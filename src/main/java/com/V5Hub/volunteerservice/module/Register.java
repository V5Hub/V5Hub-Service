package com.V5Hub.volunteerservice.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Register {
    /**
     * 唯一id
     */
    private int id;
    /**
     * 活动
     */
    private Activity activity;
    /**
     * 报名者
     */
    private String userId;
    /**
     * 报名者姓名
     */
    private String name;
    /**
     * 报名者学号
     */
    private String studentId;
    /**
     * 报名者邮箱
     */
    private String email;
    /**
     * 报名者电话
     */
    private String phoneNumber;
    /**
     * 报名者班级
     */
    private String userClass;
    /**
     * 报名者所属学院
     */
    private String college;
}
