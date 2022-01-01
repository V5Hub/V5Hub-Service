package com.example.volunteerservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.volunteerservice.module.Register;
import com.example.volunteerservice.module.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * 实现数据库中register表与Register类的映射
 *
 * @see Register
 * @version 1.0
 */
@Mapper
public interface RegisterMapper extends BaseMapper<Register>{
}
