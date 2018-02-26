package com.vastio.basic.aop;

import com.vastio.basic.common.model.Log;
import com.vastio.basic.entity.constant.ConstantEnum;
import com.vastio.basic.entity.constant.OptionalResult;
import com.vastio.basic.service.SystemLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 陈晓宇
 * @version 创建时间：2018年1月18日 上午9:14:30 类说明
 */

@Aspect
@Component
public class SystemLogAspect {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
    private static final String MODULE = "module";
    private static final String DESCRIPTION = "description";
    private static final String METHOD = "method";
    private static final String UNKNOWN = "unknown";
    private HttpServletRequest request;

    @Autowired
    private SystemLogService systemLogService;

    @Autowired
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Pointcut("@annotation(com.vastio.basic.aop.SystemLog)")
    public void methodSystemLogPointcut() {
        //系统日志切面
    }

    @AfterThrowing(pointcut = "methodSystemLogPointcut()", throwing = "e")
    public void doAfterThrowing(JoinPoint point, Throwable e) throws ClassNotFoundException {
        Log logEntity = new Log();
        Map<String, String> map;
        try {
            map = getControllerMethodDescription(point);
        } catch (ClassNotFoundException e1) {
            logger.error("记录异常日志出错，获取方法注解信息出错！", e1);
            throw e1;
        }
        logEntity.setModule(map.get(MODULE));
        logEntity.setMethod(map.get(METHOD));
        logEntity.setDescription(map.get(DESCRIPTION));
        logEntity.setTerminalId(getClientIp());
        logEntity.setErrorCode(e.toString());
        logEntity.setOptionalResult(OptionalResult.FAIL.getValue());
        systemLogService.insertLog(logEntity);

    }

    @Around("methodSystemLogPointcut()")
    public Object doController(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;
        result = joinPoint.proceed();

        try {
            Object[] args = joinPoint.getArgs();
            MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
            String[] strings = methodSignature.getParameterNames();
            StringBuilder condition = new StringBuilder();
            condition.append("[");
            for (int i = 0; i < strings.length; i++) {
                if (args[i] instanceof Model) {
                    if (i == strings.length - 1) {
                        condition.delete(condition.length() - 5, condition.length() - 1);
                    }
                    continue;
                }
                condition.append(strings[i] + " = ");
                condition.append(args[i]);
                if (i < strings.length - 1) {
                    condition.append(" and ");
                }
            }
            condition.append("]");
            Log logEntity = new Log();
            Map<String, String> map;

            map = getControllerMethodDescription(joinPoint);

            logEntity.setModule(map.get(MODULE));
            logEntity.setMethod(map.get(METHOD));
            logEntity.setDescription(map.get(DESCRIPTION) + condition.toString());
            logEntity.setTerminalId(getClientIp());
            logEntity.setOptionalResult(OptionalResult.SUCCESS.getValue());
            systemLogService.insertLog(logEntity);
        } catch (Exception e) {
            logger.error("记录操作日志失败！", e);
        }
        return result;

    }

    private String getClientIp() {

        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || UNKNOWN.equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();

            if (ip.equals(ConstantEnum.LOCAL_HOST.getValue())) {
                // 根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                    ip = inet.getHostAddress();
                } catch (UnknownHostException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        // 对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ip != null && ip.length() > 15 && ip.indexOf(',') > -1) {
            ip = ip.substring(0, ip.indexOf(','));
        }
        return ip;
    }

    public Map<String, String> getControllerMethodDescription(JoinPoint joinPoint) throws ClassNotFoundException {
        Map<String, String> map = new HashMap<>();
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    map.put(MODULE, method.getAnnotation(SystemLog.class).module());
                    map.put(METHOD, method.getAnnotation(SystemLog.class).method());
                    map.put(DESCRIPTION, method.getAnnotation(SystemLog.class).description());
                    break;
                }
            }
        }
        return map;
    }

}
