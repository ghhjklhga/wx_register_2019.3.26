package com.edu.scau.registerprovicer.serviceImpl;

import com.edu.scau.registerapi.service.NumberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NumberServiceImplTest {
    @Autowired
    NumberService numberService;

    @Test
    public void getNumberById() {
        System.out.println(numberService.getNumberById(6));
    }
}