package com.vastio.basic.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vastio.basic.common.model.AppConfig;
import com.vastio.basic.common.model.UserConfig;
import com.vastio.basic.common.service.IAppConfigService;
import com.vastio.basic.common.service.IUserConfigService;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.response.ResponseResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ConfigController extends BaseController {
    private final IUserConfigService userConfigService;

    private final IAppConfigService appConfigService;

    @Autowired
    public ConfigController(IUserConfigService userConfigService, IAppConfigService appConfigService) {
        this.userConfigService = userConfigService;
        this.appConfigService = appConfigService;
    }

    @GetMapping("/userConfig")
    public ResponseResult<UserConfig> getAllUserConfig() {
        EntityWrapper<UserConfig> wrapper = new EntityWrapper<>();
        Subject subject = SecurityUtils.getSubject();
        String userId = (String) subject.getPrincipal();
        UserConfig userConfig = new UserConfig();
        userConfig.setUserId(userId);
        wrapper.setEntity(userConfig);
        List<UserConfig> result = userConfigService.selectList(wrapper);
        return success(result, result.size());
    }

    @GetMapping("/userConfig/{key}")
    public ResponseResult<UserConfig> getUserConfigByKey(@PathVariable(value = "key") String key) {
        EntityWrapper<UserConfig> wrapper = new EntityWrapper<>();
        UserConfig userConfig = new UserConfig();
        userConfig.setName(key);
        wrapper.setEntity(userConfig);
        UserConfig result = userConfigService.selectOne(wrapper);
        return successResult(result);
    }

    @GetMapping("/appConfig")
    public ResponseResult<AppConfig> getAllAppConfig() {
        EntityWrapper<AppConfig> wrapper = new EntityWrapper<>();
        List<AppConfig> result = appConfigService.selectList(wrapper);
        return success(result, result.size());
    }

    @GetMapping("/appConfig/{key}")
    public ResponseResult<AppConfig> getAppConfigByKey(@PathVariable(value = "key") String key) {
        EntityWrapper<AppConfig> wrapper = new EntityWrapper<>();
        AppConfig appConfig = new AppConfig();
        appConfig.setName(key);
        wrapper.setEntity(appConfig);
        AppConfig result = appConfigService.selectOne(wrapper);
        return successResult(result);
    }

}
