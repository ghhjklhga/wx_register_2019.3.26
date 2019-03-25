package com.edu.scau.noticeapi.service;

import com.edu.scau.commom.form.NoticeForm;
import com.edu.scau.commom.pojo.Notice;
import com.edu.scau.commom.response.ServerResponse;

public interface NoticeService {
    public boolean addNotice(String title,String context);

    public ServerResponse deleteNotice(int id);

    public ServerResponse update(Notice notice);

    public ServerResponse getNotice(int id);

    public ServerResponse getNoticeDetailList();

    public ServerResponse getNoticeMasterList();
}
