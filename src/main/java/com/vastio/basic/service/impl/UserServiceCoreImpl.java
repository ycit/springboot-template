package com.vastio.basic.service.impl;

import com.baomidou.mybatisplus.plugins.Page;
import com.vastio.basic.common.model.Permission;
import com.vastio.basic.dao.PermissionCoreMapper;
import com.vastio.basic.dao.UserCoreMapper;
import com.vastio.basic.entity.response.UserResponse;
import com.vastio.basic.service.UserServiceCore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceCoreImpl implements UserServiceCore {
    @Autowired
    private UserCoreMapper mapper;

    @Autowired
    private PermissionCoreMapper permissionCoreMapper;

    @Override
    public Page<UserResponse> getUserInfo(Page<UserResponse> page, Map<String, Object> params) {
        return page.setRecords(mapper.selectUserResponses(page, params));
    }

    @Override
    public List<Permission> getPermissionByUser(String username) {
        return permissionCoreMapper.selectPermissionByUser(username);
    }

    @Override
    public Integer getUserId() {
        return mapper.getUserId();
    }
}
