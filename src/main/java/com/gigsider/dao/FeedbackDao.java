package com.gigsider.dao;

import com.gigsider.po.Feedback;

import java.util.List;
import java.util.Map;

public interface FeedbackDao {

    //新增反馈
    public boolean insertFeedback(Feedback feedback);

    //删除反馈
    public boolean deleteFeedback(int id);

    //更新反馈
    public boolean updateFeedback(Feedback feedback);

    //获取所有反馈
    public List<Feedback> queryAllFeedback();

    //分页查询
    public List<Feedback> queryFeedbackPage(Map<String,Object> data);

    //根据 id 查询反馈
    public List<Feedback> queryFeedbackById(int id);

    //模糊搜索
    public List<Feedback> queryFeedbackLike(String data);

    //查询未回复的反馈
    public List<Feedback> queryFeedbackNull();

    //查询已回复的反馈
    public List<Feedback> queryFeedbackNNull();

}
