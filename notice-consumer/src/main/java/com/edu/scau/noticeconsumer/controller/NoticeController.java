package com.edu.scau.noticeconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.form.NoticeForm;
import com.edu.scau.commom.pojo.Notice;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.noticeapi.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.repository.query.Param;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping(value = "/notice")
public class NoticeController {
    @Reference
    private NoticeService noticeService;

    @GetMapping("/detail/list")
    public ServerResponse getNoticeDetailList(){
       ServerResponse result = noticeService.getNoticeDetailList();
       log.info("【获取通知详情列表】获取成功！");
       return result;
    }

    @GetMapping("/master/list")
    public ServerResponse getNoticeMasterList(){
        ServerResponse response = noticeService.getNoticeMasterList();
        log.info("【获取通知简表】获取成功！");
        return response;
    }

    @GetMapping("/testNotice")
    public ServerResponse testGateway(){
        return ServerResponse.createBySuccessMessage("get notice");
    }

    @GetMapping("/send")
    public ServerResponse sendNotice(@Valid NoticeForm noticeForm, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            log.error("【发送通知】参数传入失败！noticeForm:{}",noticeForm);
            return ServerResponse.createByErrorMeeage(ResponseEnum.NOTICE_SEND_SUCCESS.getDesc());
        }
        boolean response = noticeService.addNotice(noticeForm.getTitle(),noticeForm.getContext());
        return ServerResponse.createBySuccessData(response);
    }
}
