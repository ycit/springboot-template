package com.vastio.basic.controller;

import com.vastio.basic.aop.SystemLog;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.response.ResponseResult;
import com.vastio.basic.service.CacheService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 缓存控制器
 *
 * @author xlch
 * @Date 2018-02-26 9:50
 */
@RestController
@RequestMapping("/api")
public class CacheController extends BaseController {

    private CacheService cacheService;

    @Resource
    public void setCacheService(CacheService cacheService) {
        this.cacheService = cacheService;
    }

    @SystemLog(module = "基础模块", method = "updateCache", description = "更新缓存")
    @PostMapping("/caches/{key}")
    public ResponseResult<String> update(@PathVariable("key")String key, @RequestParam("value")String value) {
        cacheService.updateByKey(key, value);
        return success("success");
    }

}
