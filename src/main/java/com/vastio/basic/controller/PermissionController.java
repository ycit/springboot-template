package com.vastio.basic.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vastio.basic.aop.SystemLog;
import com.vastio.basic.common.model.Permission;
import com.vastio.basic.common.model.RolePermission;
import com.vastio.basic.common.service.IPermissionService;
import com.vastio.basic.common.service.IRolePermissionService;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class PermissionController extends BaseController {
    private final IPermissionService permissionService;

    private final IRolePermissionService rolePermissionService;

    @Autowired
    public PermissionController(IPermissionService permissionService, IRolePermissionService rolePermissionService) {
        this.permissionService = permissionService;
        this.rolePermissionService = rolePermissionService;
    }

    @SystemLog(module = "基础模块", method = "查询所有权限", description = "查询所有权限")
    @GetMapping("/permission")
    public ResponseResult<Permission> getAllPermission() {
        EntityWrapper<Permission> wrapper = new EntityWrapper<>();
        List<Permission> result = permissionService.selectList(wrapper);
        return success(result, result.size());
    }

    @SystemLog(module = "基础模块", method = "查询角色权限", description = "根据角色查询权限")
    @GetMapping("/permission/{roleId}")
    public ResponseResult<Integer> getPermissionByRole(@PathVariable(value = "roleId") Integer id) {
        RolePermission rolePermission = new RolePermission();
        rolePermission.setRoleId(id);
        EntityWrapper<RolePermission> wrapper = new EntityWrapper<>();
        wrapper.setEntity(rolePermission);
        List<RolePermission> rolePermissionList = rolePermissionService.selectList(wrapper);
        List<Integer> result = new ArrayList<>();
        rolePermissionList.forEach(e -> result.add(e.getPermissionId()));
        return success(result, result.size());
    }

    @SystemLog(module = "基础模块", method = "更新角色权限", description = "更新角色权限")
    @PutMapping("/permission/{id}")
    public ResponseResult<String> updatePermission(@PathVariable(value = "id") Integer id,
                                                   @RequestParam(value = "permission") List<Integer> permission) {
        EntityWrapper<RolePermission> wrapper = new EntityWrapper<>();
        RolePermission condition = new RolePermission();
        condition.setRoleId(id);
        rolePermissionService.delete(wrapper);
        List<RolePermission> rolePermissionList = new ArrayList<>();
        permission.forEach(e -> {
            RolePermission rolePermission = new RolePermission();
            rolePermission.setPermissionId(e);
            rolePermission.setRoleId(id);
            rolePermission.setCreateTime(new Date());
            rolePermissionList.add(rolePermission);
        });
        if (rolePermissionService.insertBatch(rolePermissionList)) {
            return success("角色权限更新成功");
        }
        return error("角色权限更新失败", 400);
    }

}
