package com.edu.scau.userprovicer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan("com.edu.scau.userprovicer")
public class UserProvicerApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserProvicerApplication.class, args);
    }
}
