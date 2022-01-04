package com.V5Hub.volunteerservice.module;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Manager {
    /**
     * 管理员本身也是user
     */
    private User user;
    /**
     * 管理员是否已经被删除
     */
    private boolean deleted;
    /**
     * 管理员发布的活动
     */
    private List<Activity> activities;
}
