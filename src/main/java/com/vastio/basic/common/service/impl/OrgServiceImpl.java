package com.vastio.basic.common.service.impl;

import com.vastio.basic.common.model.Org;
import com.vastio.basic.common.dao.OrgMapper;
import com.vastio.basic.common.service.IOrgService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户使用的组织机构 服务实现类
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-12
 */
@Service
public class OrgServiceImpl extends ServiceImpl<OrgMapper, Org> implements IOrgService {

}
