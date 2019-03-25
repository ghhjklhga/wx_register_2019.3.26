package com.edu.scau.noticeprovicer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.edu.scau.noticeprovicer.repository")
public class NoticeProvicerApplication {

    public static void main(String[] args) {
        SpringApplication.run(NoticeProvicerApplication.class, args);
    }
}
