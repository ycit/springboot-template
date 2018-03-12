package com.vastio.basic.dao;

import com.vastio.basic.common.model.Permission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionCoreMapper {
    List<Permission> selectPermissionByUser(@Param("username") String username);
}
