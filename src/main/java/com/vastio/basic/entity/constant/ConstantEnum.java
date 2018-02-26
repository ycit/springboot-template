package com.vastio.basic.entity.constant;

/**
 * @author 陈晓宇
 * @version 创建时间：2018年1月19日 上午9:19:35 类说明
 */
public enum ConstantEnum {
    LOCAL_HOST("127.0.0.1"),
    BASE("base");
    private String value;

    ConstantEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
