package com.vastio.basic.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vastio.basic.aop.SystemLog;
import com.vastio.basic.common.model.Role;
import com.vastio.basic.common.model.User;
import com.vastio.basic.common.model.UserRole;
import com.vastio.basic.common.service.IRoleService;
import com.vastio.basic.common.service.IUserRoleService;
import com.vastio.basic.common.service.IUserService;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.requset.MenuRequest;
import com.vastio.basic.entity.response.ResponseResult;
import com.vastio.basic.util.FileUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api")
public class MenuController extends BaseController {
    @Autowired
    private IRoleService roleService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    @Value("${custom.menu-path}")
    private String menuPath;

    @SystemLog(module = "基础模块", method = "更新菜单", description = "更新菜单")
    @PutMapping("/menu")
    public ResponseResult<String> updateMenu(@RequestBody MenuRequest menuRequest) {
        String path = menuPath + File.separator + UUID.randomUUID().toString();
        FileUtil.saveFile(menuRequest.getMenu(), path);

        Role role = new Role();
        role.setPath(path);
        role.setId(menuRequest.getId());
        role.setModifyTime(new Date());
        if (roleService.updateById(role)) {
            return success("更新角色菜单成功");
        }
        return error("更新角色菜单失败", 400);
    }

    @SystemLog(module = "基础模块", method = "获取菜单", description = "获取菜单")
    @GetMapping("/menu")
    public ResponseResult<String> getMenu(@RequestParam(value = "path") String path) {
        String result = FileUtil.getFileContent(path);
        return successResult(result);
    }

    @SystemLog(module = "基础模块", method = "获取菜单", description = "获取菜单")
    @GetMapping("/defMenu")
    public ResponseResult<String> getMenu() {
        Subject currentUser = SecurityUtils.getSubject();
        String userId = (String) currentUser.getPrincipal();
        EntityWrapper<User> userEntityWrapper = new EntityWrapper<>();
        userEntityWrapper.eq("username", userId);
        User user = userService.selectOne(userEntityWrapper);

        EntityWrapper<UserRole> userRoleEntityWrapper = new EntityWrapper<>();
        userRoleEntityWrapper.eq("user_id", user.getId());

        List<UserRole> userRoles = userRoleService.selectList(userRoleEntityWrapper);
        Role role = roleService.selectById(userRoles.get(0).getRoleId());
        String result = FileUtil.getFileContent(role.getPath());
        return successResult(result);
    }


}
