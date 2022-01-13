package com.V5Hub.volunteerservice.mapper;

import com.V5Hub.volunteerservice.model.Activity;
import com.V5Hub.volunteerservice.model.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 实现根据数据库activity_tag表的多对多映射查询对应信息
 *
 * @author KirCute
 */
@Mapper
public interface ActivityTagMapper {
    /**
     * 根据activity的id来选出与该activity关联的所有tag。
     *
     * @param activity_id activity的id。
     * @return 与activity表关联的所有tag构成的列表。
     */
    @Select("SELECT * FROM tag JOIN " +
            "(SELECT * FROM activity_tag " +
            "WHERE activity_id = #{activity_id}) AS m_tag " +
            "ON m_tag.tag_id = tag.id")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "text", column = "text"),
    })
    List<Tag> selectByActivityId(@Param("activity_id") int activity_id);

    /**
     * 根据tag的id来选出与该tag关联的所有activity。
     *
     * @param tag_id tag的id。
     * @return 与该tag的关联所有activity构成的列表。
     */
    @Select("SELECT * FROM activity JOIN " +
            "(SELECT * FROM activity_tag " +
            "WHERE tag_id = #{tag_id}) AS s_activity " +
            "ON s_activity.activity_id = activity.id ")
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
    List<Activity> selectByTagId(@Param("tag_id") int tag_id);

    /**
     * 把某一对 activity-tag 关系插入到activity_tag表中。
     *
     * @param activity_id activity的id值。
     * @param tag_id tag的id值。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("INSERT IGNORE INTO activity_tag VALUES(#{activity_id}, #{tag_id})")
    int insert(@Param("activity_id") int activity_id, @Param("tag_id") int tag_id);

    /**
     * 把某一对 activity-tag 关系从activity_tag表中删除。
     * @param activity_id activity的id值。
     * @param tag_id tag的id值。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM activity_tag WHERE activity_id = #{activity_id} AND tag_id = #{tag_id}")
    int delete(@Param("activity_id") int activity_id, @Param("tag_id") int tag_id);

    /**
     * 根据activity的id来删除与该activity关联的所有连接关系。
     *
     * @param activity_id activity的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM activity_tag WHERE activity_id = #{activity_id}")
    int deleteByActivityId(@Param("activity_id") int activity_id);

    /**
     * 根据tag的id来删除与该tag关联的所有连接关系。
     *
     * @param tag_id tag的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM activity_tag WHERE tag_id = #{tag_id}")
    int deleteByTagId(@Param("tag_id") int tag_id);
}
