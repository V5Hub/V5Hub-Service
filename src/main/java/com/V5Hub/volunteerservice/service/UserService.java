package com.V5Hub.volunteerservice.service;

import com.V5Hub.volunteerservice.module.User;
import org.springframework.lang.Nullable;

import java.util.List;

/**
 * 用户User数据进行操作的接口
 *
 * @author WarmCongee
 * @version 1.0
 */
public interface UserService{

    /**
     * 此方法中获得的User列表的每一个User都包含了所有报名表，所以数据量大的时候不建议调用此方法
     *
     * @return {@link List<User>} 数据库中所有user
     */
    List<User> selectAll();


    /**
     * 根据用户id获取用户类
     *
     * @param id 用户id
     * @return {@link List<User>} 数据库中所有user
     */
    User selectById(int id);


    /**
     * 根据用户id删除数据库user表该行
     * ！！！！！！！！！！！！！！！！！同时此方法会删除该用户对应register报名表中所有报名信息
     *
     * @param id 用户id
     * @return int 删除的行数
     */
    int deleteById(int id);

    /**
     * 插入一个用户信息到数据库
     *
     * @param user 用户实体类{@link User}
     * @return int 插入的行数
     * @author WarmCongee
     */
    int insert(User user);

    /**
     * 插入多个用户信息到数据库
     *
     * @param users 用户实体类{@link List<User>}
     * @return int 插入的行数
     */
    int insert(List<User> users);

    /**
     * 跟新用户数据
     *
     * @param user 用户实体类{@link User}
     * @return int 更新的行数
     */
    int update(User user);

}
