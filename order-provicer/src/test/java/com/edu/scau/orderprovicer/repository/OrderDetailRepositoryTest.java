package com.edu.scau.orderprovicer.repository;

import com.edu.scau.commom.pojo.OrderDetail;
import com.edu.scau.commom.vo.OrderMasterVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderDetailRepositoryTest {
    @Autowired
    OrdersRepository ordersRepository;

    @Test
    public void selectOrderMasterListByOpenid() {

    }

}