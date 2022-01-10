package com.V5Hub.volunteerservice.service;

import com.V5Hub.volunteerservice.model.Activity;
import com.V5Hub.volunteerservice.model.Register;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * 对活动Activity数据进行操作的接口
 *
 * @author WarmCongee
 * @version 1.0
 */
@Repository
public interface ActivityService {
    /**
     * 获取activity表所有活动数据的实体类列表{@link List<Activity>}
     *
     * @return {@link List<Activity>} 数据库中所有activity
     */
    List<Activity> selectAll();


    /**
     * 根据活动id获取活动类
     *
     * @param id 活动id
     * @return {@link Activity} 对应活动信息的实体类
     */
    Activity selectById(int id);


    /**
     * 查找与指定时间段有交集的活动
     * @param startTime 指定时间段的开始时间
     * @param endTime 指定时间段的结束时间
     * @return {@link List<Activity>} 数据库中所有进行时间与给定时间有交集的activity
     */
    List<Activity> selectByDate(Date startTime, Date endTime);


    /**
     * 根据活动id删除数据库activity表该行
     *
     * @param id 活动id
     * @return int 删除的行数
     */
    int deleteById(int id);

    /**
     * 插入一个活动到数据库
     *
     * @param activity 活动实体类{@link Activity}
     * @return int 插入的行数
     */
    int insert(Activity activity);

    /**
     * 插入多个活动到数据库
     *
     * @param activity 活动实体类{@link List<Activity>}
     * @return int 插入的行数
     */
    int insert(List<Activity> activity);

    /**
     * 更新活动数据
     *
     * @param activity 活动实体类{@link Activity}
     * @return int 更新活动数据的行数
     */
    int update(Activity activity);
}
