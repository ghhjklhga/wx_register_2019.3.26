package com.edu.scau.orderprovicer.serviceImpl;

import com.edu.scau.commom.enums.OrderEnum;
import com.edu.scau.commom.form.OrderForm;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.orderapi.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceImplTest {
    @Autowired
    OrderService orderService;

    @Test
    public void payOrder() {
    }

    @Test
    public void cancelOrder() {
    }

    @Test
    public void getOrderList() {
        log.info("结果：{}",orderService.getOrderList("1232132321",OrderEnum.NOT_PAY.getCode()));
    }

    @Test
    public void getOrderDetail() {
    }
}