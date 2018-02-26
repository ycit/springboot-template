package com.vastio.basic.common.service.impl;

import com.vastio.basic.common.model.Permission;
import com.vastio.basic.common.dao.PermissionMapper;
import com.vastio.basic.common.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
