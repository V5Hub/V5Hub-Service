package com.example.volunteerservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("com/example/volunteerservice/mapper")
public class V5VolunteerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(V5VolunteerServiceApplication.class, args);
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local访问网址: http://localhost:" + 8080 + "\n" +
                "----------------------------------------------------------");
    }

}