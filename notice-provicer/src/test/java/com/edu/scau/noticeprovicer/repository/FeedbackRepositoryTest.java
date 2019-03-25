package com.edu.scau.noticeprovicer.repository;

import com.edu.scau.commom.pojo.Feedback;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FeedbackRepositoryTest {

    @Autowired
    FeedbackRepository feedbackRepository;

    @Test
    public void insertFeedback() {
        Feedback feedback = new Feedback();
        feedback.setId(1);
        feedback.setTitle("界面太丑");
        feedback.setContext("可以改变一下画风");
        feedback.setTime(new Date());
        feedback.setUserid(1);

        feedbackRepository.insertFeedback(feedback);

    }

    @Test
    public void selectFeedback() {
        Feedback feedback = feedbackRepository.selectFeedbackById(1);
        System.out.println(feedback.getContext());
    }

    @Test
    public void getFeedbackList() {
        List<Feedback> feedbackList = feedbackRepository.listFeedback();
        System.out.println(feedbackList.get(0).getId());
    }
}