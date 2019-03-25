package com.edu.scau.usercomsumer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource(locations = {"classpath:userconsumer.xml"})
@SpringBootApplication
//加这个注解之前没法注册
@DubboComponentScan(basePackages = "com.edu.scau.usercomsumer")
public class UserComsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserComsumerApplication.class, args);
    }
}
