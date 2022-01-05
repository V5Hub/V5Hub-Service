package com.V5Hub.volunteerservice.util.response;


import java.lang.annotation.*;


/**
 * 返回数据封装
 * 第一步注解
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
@Documented
public @interface ResponseResult {
}

