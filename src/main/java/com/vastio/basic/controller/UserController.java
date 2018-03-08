package com.vastio.basic.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.google.common.collect.ImmutableList;
import com.vastio.basic.aop.SystemLog;
import com.vastio.basic.common.model.AppConfig;
import com.vastio.basic.common.model.Permission;
import com.vastio.basic.common.model.User;
import com.vastio.basic.common.model.UserRole;
import com.vastio.basic.common.service.IAppConfigService;
import com.vastio.basic.common.service.IUserRoleService;
import com.vastio.basic.common.service.IUserService;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.requset.UserRequest;
import com.vastio.basic.entity.response.ResponseResult;
import com.vastio.basic.entity.response.UserResponse;
import com.vastio.basic.service.UserServiceCore;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author chenxy
 * @since 2018-02-06
 */
@RestController
@RequestMapping("/api")
@Transactional
public class UserController extends BaseController {
    private static final String USERNAME = "username";

    private final IUserService userService;

    private final IUserRoleService userRoleService;

    private final IAppConfigService appConfigService;

    private final UserServiceCore userServiceCore;

    @Autowired
    public UserController(IUserService userService, IUserRoleService userRoleService, IAppConfigService appConfigService, UserServiceCore userServiceCore) {
        this.userService = userService;
        this.userRoleService = userRoleService;
        this.appConfigService = appConfigService;
        this.userServiceCore = userServiceCore;
    }

    @SystemLog(module = "基础模块", method = "创建用户", description = "创建用户")
    @PostMapping("/user")
    public ResponseResult<String> addUser(@RequestBody UserRequest userRequest) {
        EntityWrapper<User> condition = new EntityWrapper<>();
        condition.eq(USERNAME, userRequest.getUsername());
        if (userService.selectCount(condition) != 0) {
            return error("用户已存在", 400);
        }
        User user = userRequest.transfer();
        user.setCreateTime(new Date());
        Integer id = userServiceCore.getUserId();
        user.setId(id);

        UserRole userRole = new UserRole();
        userRole.setUserId(id);
        userRole.setRoleId(userRequest.getRoleId());
        userRole.setCreateTime(new Date());
        if (userService.insert(user) && userRoleService.insert(userRole)) {
            return success("创建成功");
        }

        return error("未知错误", 400);
    }

    @SystemLog(module = "基础模块", method = "更新用户", description = "更新用户")
    @PutMapping("/user/{id}")
    public ResponseResult<String> updateUser(@PathVariable(value = "id") Integer id,
                                             @RequestBody UserRequest userRequset) {
        EntityWrapper<User> condition = new EntityWrapper<>();
        condition.eq(USERNAME, userRequset.getUsername());
        if (userRequset.getUsername() != null
                && !userRequset.getUsername().equals(userService.selectById(id).getUsername())
                && userService.selectCount(condition) != 0) {
            return error("用户已存在", 400);
        }
        User user = userRequset.transfer();
        user.setId(id);
        user.setModifyTime(new Date());

        EntityWrapper<UserRole> wrapper = new EntityWrapper<>();
        UserRole userRole = new UserRole();
        userRole.setUserId(id);
        userRole.setRoleId(userRequset.getRoleId());

        wrapper.eq("user_id", id);

        if (userService.updateById(user) && userRoleService.update(userRole, wrapper)) {
            return success("更新成功");
        }
        return error("未知错误", 400);
    }

    @SystemLog(module = "基础模块", method = "删除用户", description = "删除用户")
    @DeleteMapping("/user/{id}")
    public ResponseResult<String> deleteUser(@PathVariable(value = "id") Integer userId) {
        EntityWrapper<UserRole> wrapper = new EntityWrapper<>();
        UserRole userRole = new UserRole();
        userRole.setUserId(userId);
        wrapper.setEntity(userRole);
        if (userService.deleteById(userId) && userRoleService.delete(wrapper)) {
            return success("删除成功");
        }
        return error("未知错误", 400);
    }

    @SystemLog(module = "基础模块", method = "查询用户", description = "根据id查询用户")
    @GetMapping("/user/{id}")
    public ResponseResult<User> getUserById(@PathVariable(value = "id") String userId) {
        User user = userService.selectById(userId);
        user.setPassword(null);
        return successResult(user);
    }

    @SystemLog(module = "基础模块", method = "查询用户", description = "按条件查询用户")
    @GetMapping("/user")
    public ResponseResult<UserResponse> getUserResponses(@RequestParam(value = "curPage") Integer curPage,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        Page<UserResponse> page = new Page<>();
        page.setCurrent(curPage);
        page.setSize(pageSize);
        page = userServiceCore.getUserInfo(page, null);
        List<UserResponse> userResponses = page.getRecords();
        userResponses.forEach(temp -> {
            if (temp.getBName() == null) {
                temp.setBName(temp.getPName());
                temp.setPName(null);
            }
        });

        return success(page.getRecords(), page.getTotal());
    }

    @GetMapping("/user/self")
    public ResponseResult<Map<String, Object>> userInfo() {
        Page<UserResponse> page = new Page<>();
        page.setCurrent(1);
        page.setSize(1);
        Subject subject = SecurityUtils.getSubject();
        String username = (String) subject.getPrincipal();
        if (username == null) {
            return error("未获取到用户信息,请先登入.", 400);
        }

        Map<String, Object> params = new HashMap<>();
        params.put(USERNAME, username);
        page = userServiceCore.getUserInfo(page, params);

        Map<String, Object> result = new HashMap<>();
        if (page.getRecords() != null) {
            UserResponse userResponse = page.getRecords().get(0);
            result.put("user", userResponse);
        }

        List<Permission> permissions = userServiceCore.getPermissionByUser(username);
        result.put("permission", permissions);

        EntityWrapper<AppConfig> wrapper = new EntityWrapper<>();
        List<AppConfig> appConfigs = appConfigService.selectList(wrapper);
        Map<String, String> appConfig = new HashMap<>();
        appConfigs.forEach(temp -> appConfig.put(temp.getName(), temp.getValue()));
        result.put("config", appConfig);

        return success(ImmutableList.of(result), 1);
    }
}

