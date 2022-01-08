package com.V5Hub.volunteerservice.service;

import com.V5Hub.volunteerservice.model.Register;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 报名表Register数据进行操作的接口
 *
 * @version 1.0
 */
@Repository
public interface RegisterService {
    /**
     * 此方法中获得的User列表的每一个User都包含了所有报名表，所以数据量大的时候不建议调用此方法
     *
     * @return {@link List<Register>} 数据库中所有user
     */
    List<Register> selectAll();


    /**
     * 根据报名表id获取报名表类
     *
     * @param id 用户id
     * @return {@link Register} 对应报名表信息的实体类
     */
    Register selectById(int id);


    /**
     * 根据报名表删除数据库register表该行
     *
     * @param id 报名表id
     * @return int 删除的行数
     */
    int deleteById(int id);

    /**
     * 插入一个报名表到数据库
     *
     * @param register 报名表实体类{@link Register}
     * @return int 插入的行数
     */
    int insert(Register register);

    /**
     * 插入多个报名表到数据库
     *
     * @param registers 报名表实体类{@link List<Register>}
     * @return int 插入的行数
     */
    int insert(List<Register> registers);

    /**
     * 跟新报名表数据
     *
     * @param register 报名表实体类{@link Register}
     * @return int 更新报名表数据的行数
     */
    int update(Register register);
}
