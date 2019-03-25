package com.edu.scau.registerprovicer.repository;

import org.junit.Test;

import com.edu.scau.commom.pojo.Number;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class NumberRepositoryTest {
    @Autowired
    NumberRepository numberRepository;

    @Test
    public void insertNumber() {
        Number number = new Number();
        number.setSum(5);
        number.setRest(5);
        number.setDoctorid(1);

        System.out.println(numberRepository.insertNumber(number));
    }

    @Test
    public void deleteNumberByTime() {
        System.out.println(numberRepository.deleteNumberByTime(new Date()));
    }

    @Test
    public void selectNumberByDid() {
        System.out.println(numberRepository.selectNumberByDoctorid(1).get(0).getSum());
    }

    @Test
    public void update(){
        System.out.println(numberRepository.decNumberById(3));
    }

    @Test
    public void close(){
        System.out.println(numberRepository.close(3,1));
    }
}