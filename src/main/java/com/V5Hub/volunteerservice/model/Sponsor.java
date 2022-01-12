package com.V5Hub.volunteerservice.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author WarmCongee
 * @date 2022/1/12 15:32
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sponsor {
    /**
     * 主办方组织ID
     */
    private int id;
    /**
     * 主办方组织名称
     */
    private int name;
    /**
     * 主办方头像URL，暂时放着可以不用
     */
    private String picture;
}
