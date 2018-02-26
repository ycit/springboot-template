package com.vastio.basic.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.vastio.basic.common.dao.OrgMapper;
import com.vastio.basic.common.model.Org;
import com.vastio.basic.entity.OrganizationTreeVo;
import com.vastio.basic.service.OrganizationServiceCore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationServiceCoreImpl extends ServiceImpl<OrgMapper, Org> implements OrganizationServiceCore {
    public OrganizationTreeVo getOrgTree() {
        OrganizationTreeVo root = new OrganizationTreeVo(0);
        addTree(root);
        return root;
    }

    private void addTree(OrganizationTreeVo organizationTreeVo) {
        Org organization = new Org();
        organization.setParentId(organizationTreeVo.getId());
        EntityWrapper<Org> wrapper = new EntityWrapper<>();
        wrapper.setEntity(organization);
        List<Org> organizationList = selectList(wrapper);
        if (organizationList != null && !organizationList.isEmpty()) {
            if (organizationTreeVo.getOrganizationTreeVos() == null) {
                organizationTreeVo.setOrganizationTreeVos(new ArrayList<>());
            }
            organizationList.forEach(temp -> {
                OrganizationTreeVo vo = new OrganizationTreeVo();
                vo.setId(temp.getId());
                vo.setOrgName(temp.getName());
                vo.setOrgCode(temp.getCode());
                organizationTreeVo.getOrganizationTreeVos().add(vo);
                addTree(vo);
            });
        }
    }
}
