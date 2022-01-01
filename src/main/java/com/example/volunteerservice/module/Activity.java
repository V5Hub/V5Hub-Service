package com.example.volunteerservice.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Column;


import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Activity {
    /**
     * 唯一id
     */
    private int id;
    /**
     * 活动名称
     */
    private String name;
    /**
     * 活动开始时间
     */
    private Date startTime;
    /**
     * 活动结束时间
     */
    private Date endTime;
    /**
     * 活动地点
     */
    private String position;
    /**
     * 活动发起人id
     */
    private User user;
    /**
     * 活动描述
     */
    private String description;
    /**
     * 活动海报
     */
    private byte[] picture;
    /**
     * 活动信息状态
     */
    private String stateType;
}
