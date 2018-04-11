package com.vastio.basic.entity.response;

import com.vastio.basic.common.model.Log;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class LogResponse extends Log {
    private String fullName;
    private Integer orgId;
    private String pName;
    private String bName;
}
