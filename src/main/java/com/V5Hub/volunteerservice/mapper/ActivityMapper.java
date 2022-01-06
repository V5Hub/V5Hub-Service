package com.V5Hub.volunteerservice.mapper;


import com.V5Hub.volunteerservice.module.Activity;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

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
            @Result(property = "position", column = "position"),
            @Result(property = "user", column = "sponsor_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "description", column = "description"),
            @Result(property = "picture", column = "picture"),
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
            @Result(property = "position", column = "position"),
            @Result(property = "user", column = "sponsor_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "description", column = "description"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "stateType", column = "state_type"),
    })
    Activity selectById(@Param("id") int id);


    /**
     * 查找对应管理员id发布的的活动
     * @param managerId 主办方管理员id
     * @return sponsor_id=#{managerId}的Activity对象列表
     */
    @Select("SELECT * FROM activity where sponsor_id = #{managerId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "startTime", column = "start_time"),
            @Result(property = "endTime", column = "end_time"),
            @Result(property = "position", column = "position"),
            @Result(property = "user", column = "sponsor_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "description", column = "description"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "stateType", column = "state_type"),
    })
    List<Activity> selectByManagerId(@Param("managerId") String managerId);

    /**
     * 把{@link Activity}对象插入到activity表中。
     *
     * @param activity 要插入的{@link Activity}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("<script>"+
            "INSERT IGNORE INTO "+
            "activity(id, name, start_time, end_time, position, sponsor_id, description, picture, state_type) "+
            "VALUES(#{id}, #{name}, #{startTime}, #{endTime}, #{position}, " +
            "<if test='#{user}==null'> NULL </if> <if test='#{user}!=null'>#{user.id}</if>, " +
            "#{description}, #{picture}, #{stateType})"+
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
            "name = #{name},"+
            "start_time = #{startTime},"+
            "end_time = #{endTime},"+
            "position = #{position},"+
            "sponsor_id = <if test='#{user}==null'> NULL </if> <if test='#{user}!=null'>#{user.id}</if>,"+
            "description = #{description},"+
            "picture = #{picture},"+
            "state_type = #{stateType} "+
            "WHERE id = #{activity.id}"+
            " </script>")
    int update(Activity activity);

}