package com.wust.system;

/*
 * ~program: ChooseCourse
 * ~description:
 * ~author: zhuyingce
 * ~create: 2021-08-13
 */

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.wust.system.dao")
public class ChooseCourse {
    public static void main(String[] args) {

        SpringApplication.run(ChooseCourse.class, args);
    }
}
