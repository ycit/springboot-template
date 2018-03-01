package com.vastio.basic.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.vastio.basic.entity.response.UserResponse;

public interface UserServiceCore {
    Page<UserResponse> getUserInfo(Page<UserResponse> page);

    Integer getUserId();
}
