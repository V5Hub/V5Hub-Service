package com.V5Hub.volunteerservice.mapper;

import com.V5Hub.volunteerservice.model.Tag;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 实现数据库中tag表与Tag类的映射
 *
 * @author KirCute
 * @see Tag
 * @version 1.0
 */
@Mapper
public interface TagMapper {
    /**
     * 读取tag表中的所有行，并映射为Tag对象
     * @param
     * @return 包含所有tag的Tag列表
     */
    @Select("SELECT * FROM tag")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "text", column = "text"),
    })
    List<Tag> selectAll();


    /**
     * 查找对应id的tag
     * @param id tag id
     * @return id=#{id}的Tag对象
     */
    @Select("SELECT * FROM tag where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "text", column = "text"),
    })
    Tag selectById(@Param("id") int id);


    /**
     * 查找对应内容的tag
     * @param text tag的内容
     * @return text=#{text}的Tag对象
     */
    @Select("SELECT * FROM tag where text = #{text}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "text", column = "text"),
    })
    Tag selectByText(@Param("text") String text);


    /**
     * 把内容为#{text}的tag插入到tag表中。
     *
     * @param text 要插入的{@link Tag}对象的内容。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("<script>"+
            "INSERT IGNORE INTO "+
            "tag(text) "+
            "VALUES(#{text})"+
            "</script>")
    int insert(@Param("text") String text);


    /**
     * 删除tag表中id=#{id}的行。
     *
     * @param id 要删除的tag的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM tag WHERE id = #{id}")
    int deleteById(@Param("id") int id);


    /**
     * 删除tag表中text=#{text}的行。
     *
     * @param text 要删除的tag的text。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM tag WHERE text = #{text}")
    int deleteByText(@Param("text") String text);
}
