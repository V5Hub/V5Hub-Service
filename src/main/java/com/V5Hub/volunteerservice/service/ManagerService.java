package com.V5Hub.volunteerservice.service;

import com.V5Hub.volunteerservice.model.Manager;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 管理员Register数据进行操作的接口
 *
 * @author WarmCongee
 * @version 1.0
 */
@Repository
public interface ManagerService {

    /**
     * 此方法中获得的Manager列表的每一个manager都包含了所有他发布的活动和他自己的user类，所以数据量大的时候不建议调用此方法
     *
     * @return {@link List<Manager>} 数据库中所有manager的映射实体类
     */
    List<Manager> selectAll();


    /**
     * 根据管理员id获取管理员类
     *
     * @param id 用户id
     * @return {@link List<Manager>} 数据库中所有manager
     */
    Manager selectById(String id);


    /**
     * 根据manager_id删除数据库manager表该行
     *
     * @param id 用户id
     * @return int 删除的行数
     */
    int deleteById(String id);

    /**
     * 插入一个用户信息到数据库
     *
     * @param manager 用户实体类{@link Manager}
     * @return int 插入的行数
     */
    int insert(Manager manager);

    /**
     * 插入多个用户信息到数据库
     *
     * @param managers 用户实体类{@link List<Manager>}
     * @return int 插入的行数
     */
    int insert(List<Manager> managers);

    /**
     * 跟新用户数据
     *
     * @param manager 用户实体类{@link Manager}
     * @return int 更新的行数
     */
    int update(Manager manager);
}
