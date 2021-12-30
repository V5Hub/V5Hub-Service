package com.example.volunteerservice.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    /**
     * 唯一id
     */
    private int id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 学号
     */
    private String studentId;
    /**
     * 邮箱
     */
    private String email;
    /**
     * 电话
     */
    private String phoneNumber;
    /**
     * 密码
     */
    private String password;
    /**
     * 学院
     */
    private String college;
    /**
     * 用户是否已删除
     */
    private boolean deleted;
    /**
     * 头像
     */
    private byte[] picture;
}
