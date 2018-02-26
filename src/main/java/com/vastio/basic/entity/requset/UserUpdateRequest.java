package com.vastio.basic.entity.requset;

import lombok.Data;

@Data
public class UserUpdateRequest extends UserRequest {
    private Integer id;

    @Override
    public String toString() {
        return "UserRequest{"
                + "id=" + id
                + ", fullName='" + super.getFullName() + '\''
                + ", username='" + super.getUsername() + '\''
                + ", roleId=" + super.getRoleId()
                + ", orgId=" + super.getOrgId()
                + ", telNo=" + super.getTelNo()
                + '}';
    }
}
