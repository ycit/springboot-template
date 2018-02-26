package com.vastio.basic.common.service.impl;

import com.vastio.basic.common.model.User;
import com.vastio.basic.common.dao.UserMapper;
import com.vastio.basic.common.service.IUserService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
