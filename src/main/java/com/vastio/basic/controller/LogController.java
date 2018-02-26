package com.vastio.basic.controller;

import com.baomidou.mybatisplus.plugins.Page;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.LogCondition;
import com.vastio.basic.entity.constant.OptionalResult;
import com.vastio.basic.entity.response.LogResponse;
import com.vastio.basic.entity.response.ResponseResult;
import com.vastio.basic.service.SystemLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/api")
public class LogController extends BaseController {
    @Autowired
    private SystemLogService systemLogService;

    @GetMapping("/log/login")
    public ResponseResult<LogResponse> getLoginLog(@RequestParam(value = "curPage") Integer curPage,
                                                   @RequestParam(value = "pageSize") Integer pageSize,
                                                   @RequestParam(value = "orgId") Integer orgId,
                                                   @RequestParam(value = "startTime") Long startTime,
                                                   @RequestParam(value = "endTime") Long endTime) {
        LogCondition condition = new LogCondition();
        condition.setCurPage(curPage);
        condition.setPageSize(pageSize);
        condition.setOrgId(orgId);
        condition.setStartTime(new Date(startTime));
        condition.setEndTime(new Date(endTime));
        condition.setMethod("login");
        condition.setOptionalResult(OptionalResult.SUCCESS.getValue());
        Page<LogResponse> page = systemLogService.getSystemLog(condition);
        return success(page.getRecords(), page.getTotal());
    }

    @GetMapping("/log/optional")
    public ResponseResult<LogResponse> getOptionalLog(@RequestParam(value = "curPage") Integer curPage,
                                                      @RequestParam(value = "pageSize") Integer pageSize,
                                                      @RequestParam(value = "startTime") Long startTime,
                                                      @RequestParam(value = "endTime") Long endTime) {
        LogCondition condition = new LogCondition();
        condition.setCurPage(curPage);
        condition.setPageSize(pageSize);
        condition.setStartTime(new Date(startTime));
        condition.setEndTime(new Date(endTime));
        condition.setMethod("optional");
        condition.setOptionalResult(OptionalResult.SUCCESS.getValue());
        Page<LogResponse> page = systemLogService.getSystemLog(condition);
        return success(page.getRecords(), page.getTotal());
    }

    @GetMapping("/log/error")
    public ResponseResult<LogResponse> getErrorLog(@RequestParam(value = "curPage") Integer curPage,
                                                   @RequestParam(value = "pageSize") Integer pageSize,
                                                   @RequestParam(value = "startTime") Long startTime,
                                                   @RequestParam(value = "endTime") Long endTime) {
        LogCondition condition = new LogCondition();
        condition.setCurPage(curPage);
        condition.setPageSize(pageSize);
        condition.setStartTime(new Date(startTime));
        condition.setEndTime(new Date(endTime));
        condition.setOptionalResult(OptionalResult.FAIL.getValue());
        Page<LogResponse> page = systemLogService.getSystemLog(condition);
        return success(page.getRecords(), page.getTotal());
    }
}
