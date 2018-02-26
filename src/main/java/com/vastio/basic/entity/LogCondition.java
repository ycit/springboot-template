package com.vastio.basic.entity;

import lombok.Data;

import java.util.Date;

@Data
public class LogCondition {
    private Integer curPage;
    private Integer pageSize;
    private Integer orgId;
    private Date startTime;
    private Date endTime;
    private String optionalResult;
    private String method;
}
