package com.vastio.basic.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vastio.basic.aop.SystemLog;
import com.vastio.basic.common.model.Role;
import com.vastio.basic.common.service.IRoleService;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.requset.RoleRequest;
import com.vastio.basic.entity.requset.RoleUpdateRequest;
import com.vastio.basic.entity.response.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author chenxy
 * @since 2018-02-06
 */
@RestController
@RequestMapping("/api")
public class RoleController extends BaseController {
    @Autowired
    private IRoleService roleService;

    @Value("${custom.menu-path}")
    private String menuPath;

    @SystemLog(module = "基础模块", method = "角色查询", description = "根据角色名和角色描述模糊查询")
    @GetMapping("/roles")
    public ResponseResult<Role> getRoles(@RequestParam(value = "roleName", required = false) String roleName,
                                         @RequestParam(value = "roleDesc", required = false) String roleDesc) {
        EntityWrapper<Role> roleWrapper = new EntityWrapper<>();

        roleWrapper.like("description", roleDesc);
        roleWrapper.like("name", roleName);
        List<Role> result = roleService.selectList(roleWrapper);
        return success(result, result.size());

    }

    @SystemLog(module = "基础模块", method = "删除角色", description = "删除角色")
    @DeleteMapping("/role/{id}")
    public ResponseResult<String> deleteRoleById(@PathVariable(value = "id") Integer id) {
        if (roleService.deleteById(id)) {
            return success("删除角色成功");
        }
        return error("删除角色失败", 400);
    }

    @SystemLog(module = "基础模块", method = "添加角色", description = "添加角色")
    @PostMapping("/role")
    public ResponseResult<String> addRole(@RequestBody RoleRequest roleRequest) {
        Role role = roleRequest.transfer();
        role.setCreateTime(new Date());
        role.setPath(menuPath + "/default.menu");
        if (roleService.insert(role)) {
            return success("创建角色成功");
        }
        return error("创建角色失败", 400);
    }

    @SystemLog(module = "基础模块", method = "更新角色", description = "更新角色")
    @PutMapping("/role")
    public ResponseResult<String> updateRole(@RequestBody RoleUpdateRequest roleRequest) {
        Role role = roleRequest.transfer();
        role.setId(roleRequest.getId());
        role.setModifyTime(new Date());
        if (roleService.updateById(role)) {
            return success("更新角色成功");
        }
        return error("更新角色失败", 400);
    }
}

