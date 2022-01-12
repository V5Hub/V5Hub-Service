package com.V5Hub.volunteerservice.mapper;


import com.V5Hub.volunteerservice.model.Manager;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 实现数据库中manager表与Manager类的映射
 *
 * @author WarmCongee
 */
@Mapper
public interface ManagerMapper {
    /**
     * 读取manager表中的所有行，并映射为Manager对象
     * @param
     * @return 包含所有manager的Manager列表
     */
    @Select("SELECT * FROM manager")
    @Results({
            @Result(id = true, property = "id", column = "id",
                    one = @One(select = "com.V5Hub.volunteerservice.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "deleted", column = "deleted")
    })
    List<Manager> selectAll();

    /**
     * 选出manager表中对应id的行，并映射成{@link Manager}对象。
     *
     * @param id 要选出的管理员的id。
     * @return manager表中对应的管理员。
     */
    @Select("SELECT * FROM manager WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id",
                    one = @One(select = "com.V5Hub.volunteerservice.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "deleted", column = "deleted")
    })
    Manager selectById(@Param("id") String id);

    /**
     * 把{@link Manager}对象插入到manager表中。
     *
     * @param manager 要插入的{@link Manager}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("<script>" +
            "INSERT IGNORE INTO "+
            "manager(id) "+
            "VALUES(<if test='#{user}==null'> NULL </if> <if test='#{user}!=null'>#{user.id}</if>)" +
            "</script>")
    int insert(Manager manager);

    /**
     * 删除user表中id=#{id}的行。
     *
     * @param id 要删除的管理员的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM manager WHERE id = #{id}")
    int deleteById(@Param("id") String id);

//    /**
//     * 根据{@link Manager}对象的值更新对应的manager表中的行。
//     *
//     * @param manager 用于更新行的Manager对象。
//     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
//     */
//    @Update("<script>" +
//            "UPDATE manager SET "+
//            "name = #{name},"+
//            "WHERE id = #{manager.id}" +
//            "</script>")
//    int update(Manager manager);
}
