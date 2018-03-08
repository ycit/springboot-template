package com.vastio.basic.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.vastio.basic.common.model.Permission;
import com.vastio.basic.entity.response.UserResponse;

import java.util.List;
import java.util.Map;

public interface UserServiceCore {
    Page<UserResponse> getUserInfo(Page<UserResponse> page, Map<String, Object> params);

    List<Permission> getPermissionByUser(String username);

    Integer getUserId();
}
