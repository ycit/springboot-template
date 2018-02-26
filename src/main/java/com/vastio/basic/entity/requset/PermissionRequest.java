package com.vastio.basic.entity.requset;

import lombok.Data;

import java.util.List;

@Data
public class PermissionRequest {
    private Integer roleId;
    private List<Integer> permission;
}
