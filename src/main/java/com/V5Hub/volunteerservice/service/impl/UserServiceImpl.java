package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.UserMapper;
import com.V5Hub.volunteerservice.module.User;
import com.V5Hub.volunteerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author WarmCongee
 */
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int insert(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int insert(List<User> users) {
        int count = 0;
        for(var user : users){
            count += insert(user);
        }
        return count;
    }

    @Override
    public int update(User user) {
        return userMapper.update(user);
    }
}
