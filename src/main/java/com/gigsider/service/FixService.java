package com.gigsider.service;

import com.gigsider.po.Fix;

import java.util.List;

public interface FixService {
    //添加报修
    public boolean addFix(Fix fix);

    //删除报修
    public boolean delFix(int id);

    //更新报修
    public boolean upFix(Fix fix);

    //获取全部报修
    public List<Fix> getAllFix();

    //全部报修分页
    public List<Fix> allFixPage(int page, int limit);

    //根据 user_name 获取报修
    public List<Fix> getFixByUserName(String user_name);

    // user_name 分页
    public List<Fix> userNameFixPage(String user_name, int page, int limit);

    //模糊搜索
    public List<Fix> getFixLike(String like);

    //模糊搜索分页
    public List<Fix> likeFixPage(String like, int page, int limit);

    //根据 id 获取报修
    public List<Fix> getFixById(int id);
}
