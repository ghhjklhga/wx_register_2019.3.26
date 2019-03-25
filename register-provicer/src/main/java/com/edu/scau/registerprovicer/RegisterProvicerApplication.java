package com.edu.scau.registerprovicer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan(basePackages = "com.edu.scau.registerprovicer")
public class RegisterProvicerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RegisterProvicerApplication.class, args);
    }
}
