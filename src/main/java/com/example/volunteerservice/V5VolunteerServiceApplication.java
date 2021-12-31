package com.example.volunteerservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@Slf4j
@SpringBootApplication
@MapperScan("com/example/volunteerservice/mapper")
public class V5VolunteerServiceApplication {

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        SpringApplication.run(V5VolunteerServiceApplication.class, args);
//        log.info("\n----------------------------------------------------------\n\t" +
//                "Application  is running! Access URLs:\n\t" +
//                "Local访问网址: http://localhost:" + 8080 + "\n" +
//                "----------------------------------------------------------");

        MysqlHelper mysqlHelper = new MysqlHelper("volunteer", "root", "d21700499");
//        mysqlHelper.Insert("董承睿", 2020302652, "大一", "男", "计算机学院", "计算机科学与技术");
        mysqlHelper.Select();
        mysqlHelper.close();

    }

}
