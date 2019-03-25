package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Illness;
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
public class IllnessRepositoryTest {
    @Autowired
    IllnessRepository illnessRepository;

    @Test
    public void insertIllness() {
        Illness illness = new Illness();
        illness.setName("发烧");
        illness.setSectionid(1);

        System.out.println(illnessRepository.insertIllness(illness));
    }

    @Test
    public void deleteIllnessById() {
        System.out.println(illnessRepository.deleteIllnessById(1));
    }

    @Test
    public void updateIllness() {
    }

    @Test
    public void selectIllnessById() {
        System.out.println(illnessRepository.selectIllnessById(2).getName());
    }

    @Test
    public void selectIllnessByName() {
        System.out.println(illnessRepository.selectIllnessByName("发烧"));
    }

    @Test
    public void selectIllnessListByHid() {
        System.out.println(illnessRepository.selectIllnessListByHid(1).get(0).getName());
    }

    @Test
    public void selectIllnessListAll() {
        System.out.println(illnessRepository.selectIllnessListAll().get(0).getName());
    }

    @Test
    public void test(){
        log.info("结果：{}",illnessRepository.selectIllnessListBySid(1));
    }
}