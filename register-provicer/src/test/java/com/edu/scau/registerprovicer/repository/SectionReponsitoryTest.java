package com.edu.scau.registerprovicer.repository;

import com.edu.scau.commom.pojo.Illness;
import com.edu.scau.commom.pojo.Section;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
public class SectionReponsitoryTest {
    @Autowired
    SectionReponsitory sectionReponsitory;

    @Test
    public void selectSectionListAll() {
        List<Section> sectionList = sectionReponsitory.selectSectionListAll();
        log.info("结果：{}",sectionList.get(0));
    }
}