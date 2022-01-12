package com.V5Hub.volunteerservice.mapper;

import com.V5Hub.volunteerservice.model.Manager;
import com.V5Hub.volunteerservice.model.Sponsor;
import com.V5Hub.volunteerservice.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 实现根据数据库manager_sponsor表的多对多映射查询对应信息
 *
 * @author WarmCongee
 */
@Mapper
public interface ManagerSponsorMapper {

    /**
     * 根据manager的manager_id来选出与该manager关联的所有sponsor。
     *
     * @param manager_id manager的id。
     * @return 与manager表关联的所有user构成的列表。
     */
    @Select("SELECT * FROM sponsor JOIN " +
            "(SELECT * FROM manager_sponsor " +
            "WHERE manager_id = #{manager_id}) AS m_sponsor " +
            "ON m_sponsor.sponsor_id = sponsor.id")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "picture", column = "picture"),
    })
    List<Sponsor> selectByManagerId(@Param("manager_id") String manager_id);

    /**
     * 根据sponsor的id来选出与该sponsor关联的所有manager。
     *
     * @param sponsor_id sponsor组织的id。
     * @return 与该sponsor的关联所有manager构成的列表。
     */
    @Select("SELECT * FROM manager JOIN " +
            "(SELECT * FROM manager_sponsor " +
            "WHERE sponsor_id = #{sponsor_id}) AS s_manager " +
            "ON s_manager.manager_id = manager.manager_id ")
    @Results({
            @Result(id = true, property = "id", column = "id",
                    one = @One(select = "com.V5Hub.volunteerservice.mapper.UserMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "deleted", column = "deleted")
    })
    List<Manager> selectBySponsorId(@Param("sponsor_id") int sponsor_id);

    /**
     * 把某一对 manager-sponsor 关系插入到manager_sponsor表中。
     *
     * @param manager_id manager的id值。
     * @param sponsor_id sponsor的id值。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("INSERT IGNORE INTO manager_sponsor VALUES(#{manager_id}, #{sponsor_id})")
    int insert(@Param("manager_id") String manager_id, @Param("sponsor_id") int sponsor_id);

    /**
     * 把某一对 department-user 关系从department_user表中删除。
     * @param manager_id manager的id值。
     * @param sponsor_id sponsor的id值。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM manager_sponsor WHERE manager_id = #{manager_id} AND sponsor_id = #{sponsor_id}")
    int delete(@Param("manager_id") String manager_id, @Param("sponsor_id") int sponsor_id);

    /**
     * 根据manager的id来删除与该manager关联的所有连接关系。
     *
     * @param manager_id manager的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM manager_sponsor WHERE manager_id = #{manager_id}")
    int deleteByManagerId(@Param("manager_id") String manager_id);

    /**
     * 根据sponsor的id来删除与该sponsor关联的所有连接关系。
     *
     * @param sponsor_id sponsor的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM manager_sponsor WHERE sponsor_id = #{sponsor_id}")
    int deleteBySponsorId(@Param("sponsor_id") int sponsor_id);
}
