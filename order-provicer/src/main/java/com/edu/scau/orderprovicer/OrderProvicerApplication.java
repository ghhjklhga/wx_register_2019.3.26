package com.edu.scau.orderprovicer;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@DubboComponentScan("com.edu.scau.orderprovicer")
public class OrderProvicerApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderProvicerApplication.class, args);
    }
}
