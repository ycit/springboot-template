package com.vastio.basic.common.service.impl;

import com.vastio.basic.common.model.Role;
import com.vastio.basic.common.dao.RoleMapper;
import com.vastio.basic.common.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表,角色对应的菜单采用默认 服务实现类
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
