package com.gigsider.dao;

import com.gigsider.po.Fix;

import java.util.List;
import java.util.Map;

public interface FixDao {
    //添加报修
    public boolean insertFix(Fix fix);

    //删除报修
    public boolean deleteFix(int id);

    //更新报修
    public boolean updateFix(Fix fix);

    //获取全部报修
    public List<Fix> queryAllFix();

    //全部报修分页
    public List<Fix> allFixPage(Map<String,Object> data);

    //根据 user_name 获取报修
    public List<Fix> queryFixByUserName(String user_name);

    // user_name 分页
    public List<Fix> userNameFixPage(Map<String,Object> data);

    //模糊搜索
    public List<Fix> queryFixLike(String like);

    //模糊搜索分页
    public List<Fix> likeFixPage(Map<String,Object> data);

    //根据 id 获取报修
    public List<Fix> queryFixById(int id);
}
