package com.gigsider.service;

import com.gigsider.po.Comuniti;

import java.util.List;
import java.util.Map;

public interface ComunitiService {

    //新增帖子
    public boolean addComuniti(Comuniti comuniti);

    //删除帖子
    public boolean delComuniti(int id);

    //更新帖子
    public boolean upComuniti(Comuniti comuniti);

    //获取所有帖子
    public List<Comuniti> getAllComuniti();

    //分页查询
    public List<Comuniti> getComunitiPage(int page, int limit);

    //根据 id 查询帖子
    public List<Comuniti> getComunitiById(int id);

    //模糊查询分页
    public List<Comuniti> getComunitiLikePage(String like, int page, int limit);

    //模糊查询
    public List<Comuniti> getComunitiLike(String like);

    //根据 post_id 查询帖子
    public List<Comuniti> getComunitiByPostId(String post_id);

    //post_id 分页
    public List<Comuniti> getComunitiByPostIdPage(String post_id, int page, int limit);

    //获取被举报的帖子
    public List<Comuniti> getReportedComuniti();

    //举报帖子
    public boolean reportComuniti(int id);

    //取消举报
    public boolean unReportComuniti(int id);

}
