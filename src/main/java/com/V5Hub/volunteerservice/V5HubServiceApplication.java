package com.V5Hub.volunteerservice;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
@MapperScan("com/V5Hub/volunteerservice/mapper")
public class V5HubServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(V5HubServiceApplication.class, args);
        log.info("\n----------------------------------------------------------\n\t" +
                "Application  is running! Access URLs:\n\t" +
                "Local访问网址: http://localhost:" + 8080 + "\n" +
                "----------------------------------------------------------");
    }

}
