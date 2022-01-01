package com.example.volunteerservice.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.volunteerservice.module.Register;
import com.example.volunteerservice.module.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RegisterMapper extends BaseMapper<Register> {
}
