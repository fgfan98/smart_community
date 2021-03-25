package com.gigsider.service;

import com.gigsider.po.Feedback;

import java.util.List;

public interface FeedbackService {

    //添加反馈
    public boolean addFeedback(Feedback feedback);

    //删除反馈
    public boolean delFeedback(int id);

    //更新反馈
    public boolean upFeedback(Feedback feedback);

    //获取所有反馈
    public List<Feedback> getAllFeedback();

    //分页查询
    public List<Feedback> getFeedbackPage(int page, int limit);

    //根据 id 获取反馈
    public List<Feedback> getFeedbackById(int id);

    //根据 user_name 获取反馈
    public List<Feedback> getFeedbackByUserName(String user_name);

    //模糊查询
    public List<Feedback> getFeedbackLike(String data);

    //查询未回复的反馈
    public List<Feedback> getFeedbackNull();

    //查询已回复的反馈
    public List<Feedback> getFeedbackNNull();

}
