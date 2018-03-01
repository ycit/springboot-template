package com.vastio.basic.dao;

import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.vastio.basic.entity.response.UserResponse;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserCoreMapper {
    List<UserResponse> selectUserResponses(Pagination page);

    Integer getUserId();
}
