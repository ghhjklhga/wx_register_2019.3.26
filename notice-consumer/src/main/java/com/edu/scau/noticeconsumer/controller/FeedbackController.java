package com.edu.scau.noticeconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.form.FeedbackForm;
import com.edu.scau.commom.pojo.Feedback;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.noticeapi.service.FeedbackService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/feedback")
@Slf4j
public class FeedbackController {
    @Reference
    private FeedbackService feedbackService;

    @GetMapping("/list")
    public ServerResponse getFeedbackList(){
        ServerResponse result = feedbackService.getFeedbackList();

        return result;
    }

    @GetMapping("/testFeedback")
    public ServerResponse testGateway(){
        return ServerResponse.createBySuccessMessage("get feedback");
    }

    @PostMapping("/form")
    public ServerResponse submitFeedback(@Valid FeedbackForm feedbackForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.info("【提交反馈】失败！参数传入失败！");
            return ServerResponse.createByErrorMeeage(ResponseEnum.FEEDBACK_ERROR_CREATE.getDesc());
        }
        ServerResponse response = feedbackService.addFeedback(feedbackForm);
        log.info("【提交反馈】用户提交反馈{}成功！结果：{}",feedbackForm,response);
        return response;
    }

}
