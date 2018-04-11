package com.vastio.basic.entity.response;

import com.vastio.basic.common.model.User;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class UserResponse extends User {
    private String pName;
    private String bName;
}
