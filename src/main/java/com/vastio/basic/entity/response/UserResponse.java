package com.vastio.basic.entity.response;

import com.vastio.basic.common.model.User;
import lombok.Data;

@Data
public class UserResponse extends User {
    private String pName;
    private String bName;
}
