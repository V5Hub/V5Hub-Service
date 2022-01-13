package com.V5Hub.volunteerservice.mapper;

import com.V5Hub.volunteerservice.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 实现数据库中user表与User类的映射
 *
 * @see User
 * @version 1.0
 */
@Mapper
public interface UserMapper {
    /**
     * 读取user表中的所有行，并映射为User对象
     * @param
     * @return 包含所有user的User列表
     */
    @Select("SELECT * FROM user")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "openid", column = "openid"),
            @Result(property = "name", column = "name"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "password", column = "password"),
            @Result(property = "college", column = "college"),
            @Result(property = "deleted", column = "deleted"),
            @Result(property = "picture", column = "picture"),
    })
    List<User> selectAll();

    /**
     * 选出user表中对应id的行，并映射成{@link User}对象。
     *
     * @param id 要选出的用户的id。
     * @return user表中对应的用户。
     */
    @Select("SELECT * FROM user WHERE id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "openid", column = "openid"),
            @Result(property = "name", column = "name"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "password", column = "password"),
            @Result(property = "college", column = "college"),
            @Result(property = "deleted", column = "deleted"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "registers", column = "id",
                    many = @Many(select = "com.V5Hub.volunteerservice.mapper.RegisterMapper.selectByApplicantId", fetchType = FetchType.LAZY)),
    })
    User selectById(@Param("id") int id);

    /**
     * 选出user表中对应openid的行，并映射成{@link User}对象。
     *
     * @param openid 要选出的用户的openid。
     * @return user表中对应的用户。
     */
    @Select("SELECT * FROM user WHERE openid = #{openid}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "openid", column = "openid"),
            @Result(property = "name", column = "name"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "password", column = "password"),
            @Result(property = "college", column = "college"),
            @Result(property = "deleted", column = "deleted"),
            @Result(property = "picture", column = "picture"),
            @Result(property = "registers", column = "id",
                    many = @Many(select = "com.V5Hub.volunteerservice.mapper.RegisterMapper.selectByApplicantId", fetchType = FetchType.LAZY)),
    })
    User selectByOpenid(@Param("openid") String openid);

    /**
     * 把{@link User}对象插入到user表中。
     *
     * @param user 要插入的{@link User}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert(" INSERT IGNORE INTO "+
            "user(openid, name, student_id, email, phone_number, password, college, deleted, picture) "+
            "VALUES(#{openid}, #{name}, #{studentId}, #{email}, #{phoneNumber}, #{password}, #{college}, #{deleted}, #{picture})")
    int insert(User user);

    /**
     * 删除user表中id=#{id}的行。
     *
     * @param id 要删除的用户的id。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM user WHERE id = #{id}")
    int deleteById(@Param("id") int id);

    /**
     * 删除user表中openid=#{openid}的行。
     *
     * @param openid 要删除的用户的openid。
     * @return 成功从表中的删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM user WHERE openid = #{openid}")
    int deleteByOpenid(@Param("openid") String openid);

    /**
     * 根据{@link User}对象的值更新对应的user表中的行。
     *
     * @param user 用于更新行的User对象。
     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
     */
    @Update("UPDATE user SET "+
            "name = #{name},"+
            "student_id = #{studentId},"+
            "email = #{email},"+
            "phone_number = #{phoneNumber},"+
            "password = #{password},"+
            "college = #{college},"+
            "deleted = #{deleted},"+
            "picture = #{picture} "+
            "WHERE openid = #{openid}")
    int update(User user);

}

