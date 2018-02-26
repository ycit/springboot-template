package com.vastio.basic.service;

import com.baomidou.mybatisplus.service.IService;
import com.vastio.basic.common.model.Org;
import com.vastio.basic.entity.OrganizationTreeVo;

public interface OrganizationServiceCore extends IService<Org> {
    OrganizationTreeVo getOrgTree();
}
