package com.V5Hub.volunteerservice.mapper;

import com.V5Hub.volunteerservice.model.Sponsor;
import com.V5Hub.volunteerservice.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 实现数据库中sponsor表与Sponsor类的映射
 *
 * @author WarmCongee
 * @see Sponsor
 * @version 1.0
 */
@Mapper
public interface SponsorMapper {
    /**
     * 读取sponsor表中的所有行，并映射为Sponsor对象
     * @param
     * @return 包含所有sponsor的Sponsor列表
     */
    @Select("SELECT * FROM sponsor")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "picture", column = "picture"),
    })
    List<Sponsor> selectAll();

    /**
     * 选出sponsor表中对应id的行，并映射成{@link Sponsor}对象。
     *
     * @param id 要选出的组织的id。
     * @return sponsor表中对应的组织。
     */
    @Select("SELECT * FROM sponsor WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "picture", column = "picture"),
    })
    Sponsor selectById(@Param("id") int id);

    /**
     * 把{@link Sponsor}对象插入到sponsor表中。
     *
     * @param sponsor 要插入的{@link Sponsor}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert(" INSERT IGNORE INTO "+
            "sponsor(id, name, picture) "+
            "VALUES(#{id}, #{name}, #{picture})")
    int insert(Sponsor sponsor);

    /**
     * 删除sponsor表中id=#{id}的行。
     *
     * @param id 要删除的组织的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM sponsor WHERE id = #{id}")
    int deleteById(@Param("id") int id);

    /**
     * 根据{@link Sponsor}对象的值更新对应的sponsor表中的行。
     *
     * @param sponsor 用于更新行的Sponsor对象。
     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
     */
    @Update("UPDATE sponsor SET "+
            "name = #{name},"+
            "picture = #{picture} "+
            "WHERE id = #{id}")
    int update(Sponsor sponsor);
}
