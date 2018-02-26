package com.vastio.basic.security;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vastio.basic.common.model.User;
import com.vastio.basic.common.model.UserRole;
import com.vastio.basic.common.service.IUserRoleService;
import com.vastio.basic.common.service.IUserService;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

/**
 * shiro 自定义 realm
 *
 * @author xlch
 * @Date 2018-02-22 15:02
 */
public class BasicRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasicRealm.class);

    @Autowired
    private IUserService userService;

    @Autowired
    private IUserRoleService userRoleService;

    // 授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = principals.getPrimaryPrincipal().toString();
        EntityWrapper<User> userWrapper = new EntityWrapper<>();
        User user = new User();
        user.setUsername(username);
        userWrapper.setEntity(user);
        User user1 = userService.selectOne(userWrapper);
        if (null != user1) {
            EntityWrapper<UserRole> userRoleEntityWrapper = new EntityWrapper<>();
            UserRole userRole = new UserRole();
            userRole.setUserId(user1.getId());
            List<UserRole> userRoles = userRoleService.selectList(userRoleEntityWrapper);
            List<String> roles = userRoles.stream().map(userRole1 -> userRole1.getRoleId().toString()).collect(Collectors.toList());
            if (LOGGER.isDebugEnabled()) {
                LOGGER.debug("roles is ============={}", roles.stream().collect(Collectors.joining(",")));
            }
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            authorizationInfo.addRoles(roles);
            return authorizationInfo;
        }
        return null;
    }

    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();
        EntityWrapper<User> userWrapper = new EntityWrapper<>();
        User user = new User();
        user.setUsername(username);
        userWrapper.setEntity(user);
        User user1 = userService.selectOne(userWrapper);
        if (null != user1) {
            return new SimpleAuthenticationInfo(user1.getUsername(), user1.getPassword(), this.getName());
        }
        return null;
    }
}
