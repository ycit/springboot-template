package com.vastio.basic.controller;


import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.vastio.basic.aop.SystemLog;
import com.vastio.basic.common.model.Org;
import com.vastio.basic.controller.base.BaseController;
import com.vastio.basic.entity.OrganizationTreeVo;
import com.vastio.basic.entity.response.ResponseResult;
import com.vastio.basic.service.impl.OrganizationServiceCoreImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author chenxy123
 * @since 2018-02-06
 */
@RestController
@RequestMapping("/api")
public class OrganizationController extends BaseController {
    @Autowired
    private OrganizationServiceCoreImpl organizationServiceCore;

    @SystemLog(module = "基础模块", method = "查询分局", description = "查询分局")
    @GetMapping("/branch")
    public ResponseResult<Org> getBranch() {
        EntityWrapper<Org> wrapper = new EntityWrapper<>();
        wrapper.eq("parent_id", 0);
        List<Org> result = organizationServiceCore.selectList(wrapper);
        return success(result, result.size());
    }

    @SystemLog(module = "基础模块", method = "查询派出所", description = "根据分局查询派出所")
    @GetMapping("/organizations/{branchId}")
    public ResponseResult<Org> getOrganizationById(@PathVariable(value = "branchId") Integer branchId) {
        Org organization = new Org();
        organization.setParentId(branchId);
        EntityWrapper<Org> wrapper = new EntityWrapper<>();
        wrapper.setEntity(organization);
        List<Org> result = organizationServiceCore.selectList(wrapper);
        return success(result, result.size());
    }

    @SystemLog(module = "基础模块", method = "查询所有组织机构", description = "查询所有组织机构树形结构")
    @GetMapping("/organizationTree")
    public ResponseResult<OrganizationTreeVo> getOrganizationTree() {
        return successResult(organizationServiceCore.getOrgTree());
    }

}

