package com.edu.scau.userprovicer.repository;

import com.edu.scau.commom.pojo.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class UserRepositoryTest {
    @Autowired
    UserRepository userRepository;

    @Test
    public void insertUser() {
        User user = new User();
        user.setId(1);
        user.setIdnumber("445321199607033112");
        user.setUsername("伍宇轩");
        user.setPassword("asdasd");
        user.setPhone("13232147200");
        user.setAddress("广州");
        user.setOpenid("123123");

        Integer result = userRepository.insertUser(user);

        log.info("【新建用户】：结果{}",result);
    }

    @Test
    public void updateUserByOpenid() {
    }

    @Test
    public void selectUserByUserid() {
        User user = userRepository.selectUserByOpenid("123123");
        log.info("【查找用户】结果：{}",user);
    }
}