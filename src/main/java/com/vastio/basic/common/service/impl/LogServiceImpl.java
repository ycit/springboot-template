package com.vastio.basic.common.service.impl;

import com.vastio.basic.common.model.Log;
import com.vastio.basic.common.dao.LogMapper;
import com.vastio.basic.common.service.ILogService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, Log> implements ILogService {

}
