package com.edu.scau.provicer.redisprovicer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan("com.edu.scau.provicer.redisprovicer.service")
public class RedisProvicerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisProvicerApplication.class, args);
    }
}
