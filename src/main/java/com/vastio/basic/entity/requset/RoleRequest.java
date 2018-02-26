package com.vastio.basic.entity.requset;

import com.vastio.basic.common.model.Role;
import lombok.Data;

@Data
public class RoleRequest {
    private String description;
    private String name;

    public Role transfer() {
        Role role = new Role();
        role.setDescription(description);
        role.setName(name);
        return role;
    }
}
