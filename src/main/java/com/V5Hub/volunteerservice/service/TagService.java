package com.V5Hub.volunteerservice.service;

import com.V5Hub.volunteerservice.model.Tag;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 对活动Tag数据进行操作的接口
 *
 * @author KirCute
 * @version 1.0
 */
@Repository
public interface TagService {
    /**
     * 获取tag表所有tag数据的实体类列表{@link List < Tag >}
     *
     * @return {@link List<Tag>} 数据库中所有tag
     */
    List<Tag> selectAll();

    /**
     * 根据tag id获取tag类
     *
     * @param id tag id
     * @return {@link Tag} 对应tag的实体类
     */
    Tag selectById(int id);
}
