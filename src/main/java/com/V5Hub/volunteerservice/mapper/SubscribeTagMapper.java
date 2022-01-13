package com.V5Hub.volunteerservice.mapper;

import com.V5Hub.volunteerservice.model.User;
import com.V5Hub.volunteerservice.model.Tag;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 实现根据数据库subscribe_tag表的多对多映射查询对应信息
 *
 * @author KirCute
 */
@Mapper
public interface SubscribeTagMapper {
    /**
     * 根据user的id来选出与该user关联的所有tag。
     *
     * @param user_id user的id。
     * @return 与user关联的所有tag构成的列表。
     */
    @Select("SELECT * FROM tag JOIN " +
            "(SELECT * FROM subscribe_tag " +
            "WHERE user_id = #{user_id}) AS m_tag " +
            "ON m_tag.tag_id = tag.id")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "text", column = "text"),
    })
    List<Tag> selectByUserId(@Param("user_id") int user_id);

    /**
     * 根据tag的id来选出与该tag关联的所有user。
     *
     * @param tag_id tag的id。
     * @return 与该tag的关联所有user构成的列表。
     */
    @Select("SELECT * FROM user JOIN " +
            "(SELECT * FROM subscribe_tag " +
            "WHERE tag_id = #{tag_id}) AS s_user " +
            "ON s_user.user_id = user.id ")
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
    List<User> selectByTagId(@Param("tag_id") int tag_id);

    /**
     * 把某一对 user-tag 关系插入到subscribe_tag表中。
     *
     * @param user_id user的id值。
     * @param tag_id tag的id值。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("INSERT IGNORE INTO subscribe_tag VALUES(#{user_id}, #{tag_id})")
    int insert(@Param("user_id") int user_id, @Param("tag_id") int tag_id);

    /**
     * 把某一对 user-tag 关系从subscribe_tag表中删除。
     * @param user_id user的id值。
     * @param tag_id tag的id值。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM subscribe_tag WHERE user_id = #{user_id} AND tag_id = #{tag_id}")
    int delete(@Param("user_id") int user_id, @Param("tag_id") int tag_id);

    /**
     * 根据user的id来删除与该user关联的所有连接关系。
     *
     * @param user_id user的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM subscribe_tag WHERE user_id = #{user_id}")
    int deleteByUserId(@Param("user_id") int user_id);

    /**
     * 根据tag的id来删除与该tag关联的所有连接关系。
     *
     * @param tag_id tag的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM subscribe_tag WHERE tag_id = #{tag_id}")
    int deleteByTagId(@Param("tag_id") int tag_id);
}
