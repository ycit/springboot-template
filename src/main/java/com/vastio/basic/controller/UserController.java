package com.vastio.basic.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.vastio.basic.aop.SystemLog;
import com.vastio.basic.common.model.User;
import com.vastio.basic.common.service.IUserService;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.requset.UserRequest;
import com.vastio.basic.entity.requset.UserUpdateRequest;
import com.vastio.basic.entity.response.ResponseResult;
import com.vastio.basic.entity.response.UserResponse;
import com.vastio.basic.service.UserServiceCore;
import org.springframework.beans.factory.annotation.Autowired;
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

/**
 * @author chenxy
 * @since 2018-02-06
 */
@RestController
@RequestMapping("/api")
public class UserController extends BaseController {
    @Autowired
    private IUserService userService;

    @Autowired
    private UserServiceCore userServiceCore;

    @SystemLog(module = "基础模块", method = "创建用户", description = "创建用户")
    @PostMapping("/user")
    public ResponseResult<String> addUser(@RequestBody UserRequest userRequest) {
        EntityWrapper<User> condition = new EntityWrapper<>();
        condition.eq("username", userRequest.getUsername());
        if (userService.selectCount(condition) != 0) {
            return error("用户已存在", 400);
        }
        User user = userRequest.transfer();
        user.setCreateTime(new Date());
        if (userService.insert(user)) {
            return success("创建成功");
        }
        return error("未知错误", 400);
    }

    @SystemLog(module = "基础模块", method = "更新用户", description = "更新用户")
    @PutMapping("/user")
    public ResponseResult<String> updateUser(@RequestBody UserUpdateRequest userRequset) {
        EntityWrapper<User> condition = new EntityWrapper<>();
        condition.eq("username", userRequset.getUsername());
        if (userRequset.getUsername() != null
                && !userRequset.getUsername().equals(userService.selectById(userRequset.getId()).getUsername())
                && userService.selectCount(condition) != 0) {
            return error("用户已存在", 400);
        }
        User user = userRequset.transfer();
        user.setId(userRequset.getId());
        user.setModifyTime(new Date());
        if (userService.updateById(user)) {
            return success("更新成功");
        }
        return error("未知错误", 400);
    }

    @SystemLog(module = "基础模块", method = "删除用户", description = "删除用户")
    @DeleteMapping("/user/{id}")
    public ResponseResult<String> deleteUser(@PathVariable(value = "id") Integer userId) {
        if (userService.deleteById(userId)) {
            return success("删除成功");
        }
        return error("未知错误", 400);
    }

    @SystemLog(module = "基础模块", method = "查询用户", description = "根据id查询用户")
    @GetMapping("/user/{id}")
    public ResponseResult<User> getUserById(@PathVariable(value = "id") String userId) {
        User user = userService.selectById(userId);
        user.setPassword(null);
        return success(user);
    }

    @SystemLog(module = "基础模块", method = "查询用户", description = "按条件查询用户")
    @GetMapping("/user")
    public ResponseResult<UserResponse> getUserResponses(@RequestParam(value = "curPage") Integer curPage,
                                                         @RequestParam(value = "pageSize") Integer pageSize) {
        Page<UserResponse> page = new Page<>();
        page.setCurrent(curPage);
        page.setSize(pageSize);
        page = userServiceCore.getUserInfo(page);
        return success(page.getRecords(), page.getTotal());
    }
}

