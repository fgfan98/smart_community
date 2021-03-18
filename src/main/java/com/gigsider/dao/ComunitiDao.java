package com.gigsider.dao;

import com.gigsider.po.Comuniti;

import java.util.List;
import java.util.Map;

public interface ComunitiDao {

    //新增帖子
    public boolean insertComuniti(Comuniti comuniti);

    //删除帖子
    public boolean deleteComuniti(int id);

    //更新帖子
    public boolean updateComuniti(Comuniti comuniti);

    //获取所有帖子
    public List<Comuniti> queryAllComuniti();

    //分页查询
    public List<Comuniti> queryComunitiPage(Map<String,Object> data);

    //根据 id 查询帖子
    public List<Comuniti> queryComunitiById(int id);

    //模糊搜索分页
    public List<Comuniti> queryComunitiLikePage(Map<String,Object> data);

    //模糊搜索
    public List<Comuniti> queryComunitiLike(String like);

}
