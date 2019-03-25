package com.edu.scau.noticeprovicer.repository;

import com.edu.scau.commom.pojo.Notice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeRepositoryTest {

    @Autowired
    NoticeRepository noticeRepository;

    @Test
    public void findNoticeById() {
        Notice notice = noticeRepository.selectNoticeById(1);
        System.out.println(notice.getContext());
    }

    @Test
    public void addNotice() {
        Notice notice = new Notice();
        //notice.setId(5);
        notice.setTitle("停诊");
        notice.setContext("杨永信二月十八号停诊，造成不便，敬请原谅！");
        notice.setTime(new Date());

        noticeRepository.insertNotice(notice);
    }

    @Test
    public void listNotice(){
        List<Notice> list = noticeRepository.listNotice();
        Notice notice = list.get(1);
        System.out.println(notice.getContext());
    }
}