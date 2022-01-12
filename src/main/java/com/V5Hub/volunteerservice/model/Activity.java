package com.V5Hub.volunteerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.management.ObjectName;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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
    private String managerId;
    /**
     * 主办方组织
     */
    private Sponsor sponsor;
    /**
     * 活动类型tag '-'连接
     */
    private String tags;
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

    public HashMap<String, Object> parseSimpleData() {
        HashMap<String, Object> res = new HashMap<>();
        res.put("id", id);
        res.put("name", name);
        res.put("startTime", startTime);
        res.put("endTime", endTime);
        res.put("registerDeadline", registerDeadline);
        res.put("position", position);
        res.put("description", description);
        res.put("stateType", stateType);

        HashMap<String, Object> sponsor = new HashMap<>();
        sponsor.put("id", this.sponsor.getId());
        sponsor.put("name", this.sponsor.getName());
        res.put("sponsor", sponsor);

        ArrayList<HashMap<String, Object>> tagArray = new ArrayList<>();
        String[] tagTexts = tags.split("-");
        for (String tagText : tagTexts) {    // 记得在之后的操作中：1.只保留（用户更关注的）前三个tag, 2.为subscribe赋值
            HashMap<String, Object> tag = new HashMap<>();
            tag.put("text", tagText);
            tag.put("subscribe", false);
            tagArray.add(tag);
        }
        res.put("tags", tagArray);

        return res;
    }

    public HashMap<String, Object> parseDetailsData() {
        HashMap<String, Object> res = this.parseSimpleData();
        res.put("content", content);
        return res;
    }
}
