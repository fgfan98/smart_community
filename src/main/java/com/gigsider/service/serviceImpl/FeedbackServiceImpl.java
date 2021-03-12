package com.gigsider.service.serviceImpl;

import com.gigsider.dao.FeedbackDao;
import com.gigsider.po.Feedback;
import com.gigsider.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    FeedbackDao feedbackDao;

    @Override
    public boolean addFeedback(Feedback feedback) {
        return feedbackDao.insertFeedback(feedback);
    }

    @Override
    public boolean delFeedback(int id) {
        return feedbackDao.deleteFeedback(id);
    }

    @Override
    public boolean upFeedback(Feedback feedback) {
        return feedbackDao.updateFeedback(feedback);
    }

    @Override
    public List<Feedback> getAllFeedback() {
        return feedbackDao.queryAllFeedback();
    }

    @Override
    public List<Feedback> getFeedbackPage(int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return feedbackDao.queryFeedbackPage(data);
    }

    @Override
    public List<Feedback> getFeedbackById(int id) {
        return feedbackDao.queryFeedbackById(id);
    }

    @Override
    public List<Feedback> getFeedbackLike(String data) {
        return feedbackDao.queryFeedbackLike(data);
    }

    @Override
    public List<Feedback> getFeedbackNull() {
        return feedbackDao.queryFeedbackNull();
    }

    @Override
    public List<Feedback> getFeedbackNNull() {
        return feedbackDao.queryFeedbackNNull();
    }
}
