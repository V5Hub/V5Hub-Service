package com.example.volunteerservice;

import com.example.volunteerservice.mapper.*;
import com.example.volunteerservice.module.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;


@RunWith(SpringRunner.class)
@MapperScan("com/example/volunteerservice/mapper")
@SpringBootTest
public class V5VolunteerServiceApplicationTests {


    private final UserMapper userMapper;

    @Autowired
    public V5VolunteerServiceApplicationTests(UserMapper userMapper){
        this.userMapper = userMapper;
    }

    @Test
    public void testSelect() {
        System.out.println(("----- selectAll method test ------"));
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        System.out.println("pass");
    }
}
