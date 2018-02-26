package com.vastio.basic.common.service.impl;

import com.vastio.basic.common.model.UserConfig;
import com.vastio.basic.common.dao.UserConfigMapper;
import com.vastio.basic.common.service.IUserConfigService;
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
public class UserConfigServiceImpl extends ServiceImpl<UserConfigMapper, UserConfig> implements IUserConfigService {

}
