package com.vastio.basic.aop;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author 陈晓宇
 * @version 创建时间：2018年1月18日 上午9:08:49 类说明
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SystemLog {
    //模块名称
    String module() default "";

    //方法名称
    String method() default "";

    //操作描述
    String description() default "";

}
