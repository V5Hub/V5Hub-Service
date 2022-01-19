package com.V5Hub.volunteerservice.service.impl;

import com.V5Hub.volunteerservice.mapper.RegisterMapper;
import com.V5Hub.volunteerservice.mapper.SubscribeTagMapper;
import com.V5Hub.volunteerservice.mapper.UserMapper;
import com.V5Hub.volunteerservice.model.Register;
import com.V5Hub.volunteerservice.model.User;
import com.V5Hub.volunteerservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author WarmCongee
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final RegisterMapper registerMapper;

    @Autowired
    public UserServiceImpl(UserMapper userMapper, RegisterMapper registerMapper){
        this.userMapper = userMapper;
        this.registerMapper = registerMapper;
    }

    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public User selectByOpenid(String openid) {
        return userMapper.selectByOpenid(openid);
    }

    @Override
    public User selectByIdWithRegisters(int id) {
        return userMapper.selectByIdWithRegisters(id);
    }

    @Override
    public User selectByOpenidWithRegisters(String openid) {
        return userMapper.selectByOpenidWithRegisters(openid);
    }

    @Override
    public Register selectByUserIdActivityId(int userId, int activityId){
        return registerMapper.selectByUserIdActivityId(userId, activityId);
    }

    @Override
    public int deleteById(int id) {
        return userMapper.deleteById(id);
    }

    @Override
    public int deleteByOpenid(String openid) {
        return userMapper.deleteByOpenid(openid);
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
        // TODO: 对subscribedTags进行保存
        return userMapper.update(user);
    }

    @Override
    public User newUserLogin(String openId){
        return new User(0, openId,"",null,null,null,null,null,null,false,null,null);
    }
}
