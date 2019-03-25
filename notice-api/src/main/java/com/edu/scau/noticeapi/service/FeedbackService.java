package com.edu.scau.noticeapi.service;

import com.edu.scau.commom.form.FeedbackForm;
import com.edu.scau.commom.pojo.Feedback;
import com.edu.scau.commom.response.ServerResponse;

public interface FeedbackService {
    ServerResponse addFeedback(FeedbackForm feedbackForm);

    void delFeedback(int id);

    void updateFeedback(Feedback feedback);

    ServerResponse getFeedback(int id);

    ServerResponse getFeedbackList();
}
