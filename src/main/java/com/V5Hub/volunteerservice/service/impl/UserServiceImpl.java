package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.UserMapper;
import com.V5Hub.volunteerservice.module.User;
import com.V5Hub.volunteerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WarmCongee
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public int deleteById(String id) {
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

    @Override
    public User newUserLogin(String openId){
        return new User(openId,"",null,null,null,null,null,false,null,null);
    }
}
