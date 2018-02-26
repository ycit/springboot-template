package com.vastio.basic.common.service.impl;

import com.vastio.basic.common.model.AppConfig;
import com.vastio.basic.common.dao.AppConfigMapper;
import com.vastio.basic.common.service.IAppConfigService;
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
public class AppConfigServiceImpl extends ServiceImpl<AppConfigMapper, AppConfig> implements IAppConfigService {

}
