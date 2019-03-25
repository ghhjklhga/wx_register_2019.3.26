package com.edu.scau.noticeprovicer.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.edu.scau.commom.enums.ResponseEnum;
import com.edu.scau.commom.exception.RegisterException;
import com.edu.scau.commom.form.NoticeForm;
import com.edu.scau.commom.pojo.Notice;
import com.edu.scau.commom.response.ServerResponse;
import com.edu.scau.commom.vo.NoticeDetailVO;
import com.edu.scau.commom.vo.NoticeMasterVO;
import com.edu.scau.noticeapi.service.NoticeService;
import com.edu.scau.noticeprovicer.repository.NoticeRepository;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class NoticeServiceImpl implements NoticeService {
    @Autowired
    private NoticeRepository noticeRepository;

    //  添加通知
    @Override
    public boolean addNotice(String title,String context) {
        Notice notice = new Notice();
        notice.setTitle(title);
        notice.setContext(context);
        notice.setTime(new Date());
        //  插入数据库
        Integer result = noticeRepository.insertNotice(notice);
        if (result != 1){
            log.error("【添加通知】失败！结果：{}",result);
            return false;
        }
        return true;
    }

    @Override
    public ServerResponse deleteNotice(int id) {
        return null;
    }

    @Override
    public ServerResponse update(Notice notice) {
        return null;
    }

    @Override
    public ServerResponse getNotice(int id) {
        return null;
    }

    @Override
    public ServerResponse getNoticeDetailList() {
        List<Notice> noticeList = noticeRepository.listNotice();
        List<NoticeDetailVO> noticeDetailVOList = noticeList.stream().map( e ->
            new NoticeDetailVO(e.getId(),e.getTitle(),e.getContext(),e.getTime()))
                .collect(Collectors.toList());
        return ServerResponse.createBySuccessData(noticeDetailVOList);
    }

    @Override
    public ServerResponse getNoticeMasterList() {
        List<Notice> noticeList = noticeRepository.listNotice();
        List<NoticeMasterVO> noticeMasterVOList = noticeList.stream().map( e ->
            new NoticeMasterVO(e.getId(), e.getTitle(), e.getTime()))
                .collect(Collectors.toList());
        return ServerResponse.createBySuccessData(noticeMasterVOList);
    }
}
