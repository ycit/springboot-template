package com.vastio.basic.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.vastio.basic.dao.UserCoreMapper;
import com.vastio.basic.entity.response.UserResponse;
import com.vastio.basic.service.UserServiceCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceCoreImpl implements UserServiceCore {
    @Autowired
    private UserCoreMapper mapper;

    @Override
    public Page<UserResponse> getUserInfo(Page<UserResponse> page) {
        return page.setRecords(mapper.selectUserResponses(page));
    }

    @Override
    public Integer getUserId() {
        return mapper.getUserId();
    }
}
