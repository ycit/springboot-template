package com.vastio.basic.entity.requset;

import com.vastio.basic.common.model.User;
import com.vastio.basic.util.CommonUtil;
import lombok.Data;

@Data
public class UserRequest {
    private String fullName;
    private String username;
    private Integer roleId;
    private Long orgId;
    private String password;
    private Long telNo;

    public User transfer() {
        User user = new User();
        user.setFullName(this.fullName);
        if (password != null) {
            user.setPassword(CommonUtil.encoderByMD5(this.password));
        }
        user.setOrgId(this.orgId);
        user.setRoleId(this.roleId);
        user.setUsername(this.username);
        user.setTelNo(this.telNo);
        return user;
    }

    @Override
    public String toString() {
        return "UserRequest{"
                + "fullName='" + fullName + '\''
                + ", username='" + username + '\''
                + ", roleId=" + roleId
                + ", orgId=" + orgId
                + ", telNo=" + telNo
                + '}';
    }
}
