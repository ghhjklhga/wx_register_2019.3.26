package com.edu.scau.orderprovicer.repository;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrdersRepositoryTest {
    @Autowired
    private OrdersRepository ordersRepository;

    @Test
    public void insertOrderDetail() {
    }

    @Test
    public void selectOrderDetailListByOpenid() {
        log.info("结果： {}",ordersRepository.selectOrderDetailListByOpenid("1232132321"));
    }

    @Test
    public void selectOrderDetailByOrderid() {
    }

    @Test
    public void payOrderDetail() {
    }

    @Test
    public void cancelOrderDetail() {
    }
}