package com.V5Hub.volunteerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

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
     * 活动结束时间
     */
    private Date registerDeadline;
    /**
     * 活动地点
     */
    private String position;
    /**
     * 活动级别
     */
    private String level;
    /**
     * 活动发起Manager的ID
     */
    private int managerId;
    /**
     * 主办方组织
     */
    private Sponsor sponsor;
    /**
     * 活动tag
     */
    private List<Tag> tags;
    /**
     * 活动简单描述
     */
    private String description;
    /**
     * 活动具体内容/推文
     */
    private String content;
    /**
     * 活动海报相对路径URL
     */
    private String picture;
    /**
     * 活动海报相对路径URL
     */
    private String pictureHorizontal;
    /**
     * 活动信息状态
     */
    private int stateType;
}
