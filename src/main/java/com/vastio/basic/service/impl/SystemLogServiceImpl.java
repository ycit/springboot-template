package com.vastio.basic.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.vastio.basic.common.model.Log;
import com.vastio.basic.common.service.ILogService;
import com.vastio.basic.dao.LogCoreMapper;
import com.vastio.basic.entity.LogCondition;
import com.vastio.basic.entity.response.LogResponse;
import com.vastio.basic.service.SystemLogService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @author 陈晓宇
 * @version 创建时间：2018年1月18日 下午2:02:48 类说明
 */

@Service
public class SystemLogServiceImpl implements SystemLogService {
    private static final Logger logger = LoggerFactory.getLogger(SystemLogServiceImpl.class);

    @Autowired
    private ILogService logService;

    @Autowired
    private LogCoreMapper logCoreMapper;

    @Override
    public void insertLog(Log log) {
        log.setOptionalTime(new Date());
        Subject currentUser = SecurityUtils.getSubject();
        String userId = (String) currentUser.getPrincipal();
        if (logger.isDebugEnabled()) {
            logger.debug("userid = {}" + userId);
        }
        if (userId == null) {
            userId = "0";
            logger.error("Cannot get User info. Use id=0 instead.");
        }
        log.setUserId(userId);
        if (logger.isDebugEnabled()) {
            logger.debug("log content:{}" + log.toString());
        }
        logService.insert(log);
    }

    @Override
    public Page<LogResponse> getSystemLog(LogCondition condition) {
        Page<LogResponse> page = new Page<>();
        page.setCurrent(condition.getCurPage());
        page.setSize(condition.getPageSize());
        page.setRecords(logCoreMapper.selectLogResponses(page, condition.getOrgId(), condition.getStartTime(),
                condition.getEndTime(), condition.getOptionalResult(), condition.getMethod()));
        return page;
    }

}
