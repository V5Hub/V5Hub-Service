package com.V5Hub.volunteerservice.mapper;


import com.V5Hub.volunteerservice.model.Activity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.Date;
import java.util.List;

/**
 * 实现数据库中activity表与Activity类的映射
 *
 * @author WarmCongee
 * @see Activity
 * @version 1.0
 */
@Mapper
public interface ActivityMapper {
    /**
     * 读取activity表中的所有行，并映射为Activity对象
     * @param
     * @return 包含所有activity的Activity列表
     */
    @Select("SELECT * FROM activity")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "registerDeadline", column = "register_deadline"),
            @Result(property = "position", column = "position"),
            @Result(property = "level", column = "level"),
            @Result(property = "managerId", column = "manager_id"),
            @Result(property = "sponsor", column = "sponsor_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.SponsorMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "tags", column = "tag_id", many = @Many(select = "com.V5Hub.volunteerservice.mapper.ActivityTagMapper.selectByActivityId", fetchType = FetchType.LAZY)),
            @Result(property = "description", column = "description"),
            @Result(property = "content", column = "content"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "pictureHorizontal", column = "picture_horizontal"),
            @Result(property = "stateType", column = "state_type"),
    })
    List<Activity> selectAll();


    /**
     * 查找对应id的活动
     * @param id 活动id
     * @return id=#{id}的Activity对象
     */
    @Select("SELECT * FROM activity where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "registerDeadline", column = "register_deadline"),
            @Result(property = "position", column = "position"),
            @Result(property = "level", column = "level"),
            @Result(property = "managerId", column = "manager_id"),
            @Result(property = "sponsor", column = "sponsor_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.SponsorMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "tags", column = "tag_id", many = @Many(select = "com.V5Hub.volunteerservice.mapper.ActivityTagMapper.selectByActivityId", fetchType = FetchType.LAZY)),
            @Result(property = "description", column = "description"),
            @Result(property = "content", column = "content"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "pictureHorizontal", column = "picture_horizontal"),
            @Result(property = "stateType", column = "state_type"),
    })
    Activity selectById(@Param("id") int id);


    /**
     * 查找与指定时间段有交集的活动
     * @param startTime 指定时间段的开始时间
     * @param endTime 指定时间段的结束时间
     * @return (start_time, end_time)与(#{startTime}, #{endTime})有交集的Activity对象列表
     */
    @Select("SELECT * FROM activity where not ((end_time < #{startTime}) or (start_time > #{endTime})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "registerDeadline", column = "register_deadline"),
            @Result(property = "position", column = "position"),
            @Result(property = "level", column = "level"),
            @Result(property = "managerId", column = "manager_id"),
            @Result(property = "sponsor", column = "sponsor_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.SponsorMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "tags", column = "tag_id", many = @Many(select = "com.V5Hub.volunteerservice.mapper.ActivityTagMapper.selectByActivityId", fetchType = FetchType.LAZY)),
            @Result(property = "description", column = "description"),
            @Result(property = "content", column = "content"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "pictureHorizontal", column = "picture_horizontal"),
            @Result(property = "stateType", column = "state_type"),
    })
    List<Activity> selectByDate(@Param("startTime") Date startTime, @Param("endTime") Date endTime);


    /**
     * 查找对应管理员id发布的的活动
     * @param managerId 主办方管理员id
     * @return sponsor_id=#{managerId}的Activity对象列表
     */
    @Select("SELECT * FROM activity where manager_id = #{managerId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "registerDeadline", column = "register_deadline"),
            @Result(property = "position", column = "position"),
            @Result(property = "level", column = "level"),
            @Result(property = "managerId", column = "manager_id"),
            @Result(property = "sponsor", column = "sponsor_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.SponsorMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "tags", column = "tag_id", many = @Many(select = "com.V5Hub.volunteerservice.mapper.ActivityTagMapper.selectByActivityId", fetchType = FetchType.LAZY)),
            @Result(property = "description", column = "description"),
            @Result(property = "content", column = "content"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "pictureHorizontal", column = "picture_horizontal"),
            @Result(property = "stateType", column = "state_type"),
    })
    List<Activity> selectByManagerId(@Param("managerId") int managerId);

    /**
     * 把{@link Activity}对象插入到activity表中。
     *
     * @param activity 要插入的{@link Activity}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("<script>"+
            "INSERT IGNORE INTO "+
            "activity(name, start_time, end_time, register_deadline, position, level, manager_id, sponsor_id, description, content, picture, picture_horizontal, state_type) "+
            "VALUES(#{name}, #{startTime}, #{endTime}, #{registerDeadline}, #{position}, #{level}, #{managerId}," +
            "<if test='#{sponsor}==null'> NULL </if> <if test='#{sponsor}!=null'>#{sponsor.id}</if>, " +
            "#{description}, #{content}, #{picture}, #{pictureHorizontal}, #{stateType})"+
            "</script>")
    int insert(Activity activity);

    /**
     * 删除activity表中id=#{id}的行。
     *
     * @param id 要删除的活动的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM activity WHERE id = #{id}")
    int deleteById(@Param("id") int id);

    /**
     * 根据{@link Activity}对象的值更新对应的user表中的行。
     *
     * @param activity 用于更新行的Activity对象。
     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
     */
    @Update("<script> "+
            "UPDATE activity SET "+
            "name = #{name}, "+
            "start_time = #{startTime}, "+
            "end_time = #{endTime}, " +
            "register_deadline = #{registerDeadline}, "+
            "position = #{position}, " +
            "level = #{level}, " +
            "manager_id = #{managerId}, "+
            "sponsor_id = <if test='#{sponsor}==null'> NULL </if> <if test='#{sponsor}!=null'>#{sponsor.id}</if>, " +
            "description = #{description},"+
            "content = #{content}, "+
            "picture = #{picture},"+
            "picture_horizontal = #{pictureHorizontal}, "+
            "state_type = #{stateType} "+
            "WHERE id = #{activity.id}"+
            " </script>")
    int update(Activity activity);

}
