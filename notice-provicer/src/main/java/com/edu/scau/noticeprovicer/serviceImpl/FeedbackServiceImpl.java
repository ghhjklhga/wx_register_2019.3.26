package com.edu.scau.noticeprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.form.FeedbackForm;
import com.edu.scau.commom.pojo.Feedback;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.noticeapi.service.FeedbackService;
import com.edu.scau.noticeprovicer.repository.FeedbackRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class FeedbackServiceImpl implements FeedbackService {
    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public ServerResponse addFeedback(FeedbackForm feedbackForm) {
        Feedback feedback = buildFeedback(feedbackForm);
        Integer result = feedbackRepository.insertFeedback(feedback);
        if (result != 1){
            log.error("【新建反馈】失败！结果：{}",result);
            throw new RegisterException(ResponseEnum.FEEDBACK_ERROR_CREATE.getDesc());
        }
        return ServerResponse.createBySuccessMessage(ResponseEnum.FEEDBACK_SUCCESS_CREATE.getDesc());
    }

    private Feedback buildFeedback(FeedbackForm feedbackForm) {
        Feedback feedback = new Feedback();
        feedback.setUserid(feedbackForm.getUserid());
        feedback.setTime(new Date());
        feedback.setContext(feedbackForm.getContext());
        feedback.setContact(feedbackForm.getContact());
        feedback.setTitle(feedbackForm.getTitle());
        return feedback;
    }

    @Override
    public void delFeedback(int id) {

    }

    @Override
    public void updateFeedback(Feedback feedback) {

    }

    @Override
    public ServerResponse getFeedback(int id) {
        return null;
    }

    @Override
    public ServerResponse getFeedbackList() {
        List<Feedback> feedbackList = feedbackRepository.listFeedback();
        PageHelper.startPage(1, 10);
        PageInfo<Feedback> feedbackPageInfo = new PageInfo<>(feedbackList);

        return ServerResponse.createBySuccessData(feedbackPageInfo);
    }
}
