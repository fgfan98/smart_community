package com.gigsider.service;

import com.gigsider.po.Comuniti;

import java.util.List;

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

}
