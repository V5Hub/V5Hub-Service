package com.V5Hub.volunteerservice.mapper;

import com.V5Hub.volunteerservice.model.Register;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;


/**
 * 实现数据库中register表与Register类的映射
 *
 * @see Register
 * @version 1.0
 */
@Mapper
public interface RegisterMapper{

    /**
     * 读取register表中的所有行，并映射为Register对象
     * @param
     * @return 包含所有register的Register列表
     */
    @Select("SELECT * FROM register")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "activity", column = "activity_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.ActivityMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "userId", column = "applicant_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "userClass", column = "user_class"),
            @Result(property = "college", column = "college"),
            @Result(property = "state", column = "state"),
    })
    List<Register> selectAll();


    /**
     * 查找对应id的报名表
     * @param id 活动id
     * @return id=#{id}的Register对象
     */
    @Select("SELECT * FROM register where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "activity", column = "activity_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.ActivityMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "userId", column = "applicant_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "userClass", column = "user_class"),
            @Result(property = "college", column = "college"),
            @Result(property = "state", column = "state"),
    })
    Register selectById(@Param("id") int id);

    /**
     * 对应用户和对应活动的报名表
     * @param applicantId 用户id
     * @param activityId 活动id
     * @return Register对象
     */
    @Select("SELECT * FROM register where applicant_id = #{applicantId} and activity_id = #{activityId} ")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "activity", column = "activity_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.ActivityMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "userId", column = "applicant_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "userClass", column = "user_class"),
            @Result(property = "college", column = "college"),
            @Result(property = "state", column = "state"),
    })
    Register selectByUserIdActivityId(@Param("applicantId") int applicantId, @Param("activityId") int activityId);

    /**
     * 查找用户id的所有报名表
     * @param applicantId 用户id
     * @return applicant_id = #{applicantId}的Register对象
     */
    @Select("SELECT * FROM register where applicant_id = #{applicantId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "activity", column = "activity_id", one = @One(select = "com.V5Hub.volunteerservice.mapper.ActivityMapper.selectById", fetchType = FetchType.LAZY)),
            @Result(property = "userId", column = "applicant_id"),
            @Result(property = "name", column = "name"),
            @Result(property = "studentId", column = "student_id"),
            @Result(property = "email", column = "email"),
            @Result(property = "phoneNumber", column = "phone_number"),
            @Result(property = "userClass", column = "user_class"),
            @Result(property = "college", column = "college"),
            @Result(property = "state", column = "state"),
    })
    List<Register> selectByApplicantId(@Param("applicantId") int applicantId);

    /**
     * 把{@link Register}对象插入到register表中。
     *
     * @param register 要插入的{@link Register}对象。
     * @return 成功插入到表中的行的数目，若插入失败或插入被忽略，则返回0。
     */
    @Insert("<script>"+
            "INSERT IGNORE INTO "+
            "register(activity_id, applicant_id, name, student_id, email, phone_number, user_class, college, state) "+
            "VALUES( " +
            "<if test='#{activity}==null'> NULL </if> <if test='#{activity}!=null'>#{activity.id}</if>, " +
            "#{userId}, " +
            "#{name}, " +
            "#{studentId}, " +
            "#{email}, " +
            "#{phoneNumber}, " +
            "#{userClass}, " +
            "#{college}, " +
            "#{state})"+
            "</script>")
    int insert(Register register);

    /**
     * 删除register表中id=#{id}的行。
     *
     * @param id 要删除的报名表的id。
     * @return 成功从表中删除的行的数目，若没有删除任何行，则返回0。
     */
    @Delete("DELETE FROM register WHERE id = #{id}")
    int deleteById(@Param("id") int id);

    /**
     * 根据{@link Register}对象的值更新对应的register表中的行。
     *
     * @param register 用于更新行的Register对象。
     * @return 成功在表中的更新的行数目，若没有更新任何行，则返回0。
     */
    @Update("<script> "+
            "UPDATE register SET "+
            "activity_id = <if test='#{activity}==null'> NULL </if> <if test='#{activity}!=null'>#{activity.id}</if>, "+
            "applicant_id = #{userId}, "+
            "name = #{name}, "+
            "student_id = #{studentId}, "+
            "email =  #{email}, "+
            "phone_number = #{phoneNumber}, "+
            "user_class = #{userClass}, "+
            "college = #{college} " +
            "state = #{state} "+
            "WHERE id = #{register.id} "+
            "</script>")
    int update(Register register);

}
