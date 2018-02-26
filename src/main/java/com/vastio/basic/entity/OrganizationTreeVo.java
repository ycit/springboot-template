package com.vastio.basic.entity;

import lombok.Data;

import java.util.List;

@Data
public class OrganizationTreeVo {
    private Integer id;
    private String orgName;
    private String orgCode;
    private List<OrganizationTreeVo> organizationTreeVos;

    public OrganizationTreeVo() {
    }

    public OrganizationTreeVo(Integer id) {
        this.id = id;
    }

}
