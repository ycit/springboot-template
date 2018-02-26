package com.vastio.basic.entity.constant;

/**
 * @author 陈晓宇
 * @version 创建时间：2018年1月19日 上午11:15:07 类说明
 */
public enum OptionalResult {
    SUCCESS("0"),
    FAIL("1");

    private String value;

    OptionalResult(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
