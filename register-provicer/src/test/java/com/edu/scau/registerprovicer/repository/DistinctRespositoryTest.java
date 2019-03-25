package com.edu.scau.registerprovicer.repository;

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
public class DistinctRespositoryTest {
    @Autowired
    DistinctRespository distinctRespository;

    @Test
    public void insertDistinct() {
    }

    @Test
    public void selectDistinctAll() {
        log.info("{}",distinctRespository.selectDistinctAll());
    }
}