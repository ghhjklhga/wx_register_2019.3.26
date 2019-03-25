package com.edu.scau.registerconsumer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//加这个注解之前没法注册
@DubboComponentScan(basePackages = "com.edu.scau.registerconsumer")
public class RegisterConsumerApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegisterConsumerApplication.class, args);
    }
}
