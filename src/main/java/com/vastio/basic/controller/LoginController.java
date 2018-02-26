package com.vastio.basic.controller;

import com.vastio.basic.aop.SystemLog;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.form.LoginForm;
import com.vastio.basic.entity.response.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * 登入登出
 *
 * @author xlch
 * @Date 2018-02-22 18:00
 */
@RestController
@RequestMapping("/api")
public class LoginController extends BaseController {

    @SystemLog(module = "基础模块", method = "login", description = "用户登入")
    @PostMapping(value = "/login")
    public ResponseResult<String> login(@Valid LoginForm loginForm, BindingResult result) {
        if (result.hasErrors()) {
            return error("用户信息不合法", 400);
        }
        String username = loginForm.getUsername();
        String pd = loginForm.getPassword();
        UsernamePasswordToken token = new UsernamePasswordToken(username, pd);
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(token);
        } catch (UnknownAccountException u) {
            return error("该用户不存在", 400);
        } catch (IncorrectCredentialsException i) {
            return error("密码与账号不匹配", 400);
        } catch (AuthenticationException a) {
            return error("登录信息不正确", 400);
        } catch (Exception e) {
            return error("服务内部错误", 400);
        }
        return success("login success");
    }

    @SystemLog(module = "基础模块", method = "退出登入", description = "退出登入")
    @GetMapping(value = "/logout")
    public void logout() {
        Subject currentUser=SecurityUtils.getSubject();
        if (currentUser.isAuthenticated()) {
            currentUser.logout();
        }
    }

}
