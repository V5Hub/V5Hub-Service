package com.example.volunteerservice;

import com.example.volunteerservice.mapper.*;
import com.example.volunteerservice.module.Activity;
import com.example.volunteerservice.module.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@MapperScan("com/example/volunteerservice/mapper")
@SpringBootTest
public class V5VolunteerServiceApplicationTests {


    private final UserMapper userMapper;
    private final ActivityMapper activityMapper;

    @Autowired
    public V5VolunteerServiceApplicationTests(UserMapper userMapper, ActivityMapper activityMapper){
        this.userMapper = userMapper;
        this.activityMapper = activityMapper;
    }

    @Test
    public void testUserMapper() {
        log.info("----- UserMapper method test ------");

        List<User> userList = userMapper.selectAll();
        userList.forEach(System.out::println);

        User user = new User(5,"test5","2020302020",
                null,null,"test5",null,false,null);
        userMapper.insert(user);
        List<User> newUserList = userMapper.selectAll();
        newUserList.forEach(System.out::println);

        userMapper.deleteById(5);
        newUserList = userMapper.selectAll();
        newUserList.forEach(System.out::println);

        User user1 = userMapper.selectById(4);
        log.info(user1.toString());

        log.info("pass");
    }

    @Test
    public void testActivityMapper() {
        log.info("----- ActivityMapper method test ------");

        List<Activity> activityList = activityMapper.selectAll();
        activityList.forEach(System.out::println);

        Activity activity = new Activity(5,"体育活动",null,null,null,userMapper.selectById(1),null,null,"passed");
        activityMapper.insert(activity);
        activityList = activityMapper.selectAll();
        activityList.forEach(System.out::println);

        activityMapper.deleteById(5);
        activityList = activityMapper.selectAll();
        activityList.forEach(System.out::println);
    }
}
