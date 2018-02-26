package com.vastio.basic.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.vastio.basic.common.model.Log;
import com.vastio.basic.entity.LogCondition;
import com.vastio.basic.entity.response.LogResponse;

/**
 * @author 陈晓宇
 * @version 创建时间：2018年1月18日 下午2:01:37 类说明
 */
public interface SystemLogService {
    void insertLog(Log log);

    Page<LogResponse> getSystemLog(LogCondition condition);
}
