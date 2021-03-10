package com.gigsider.dao;

import com.gigsider.po.Bulletin;

import java.util.List;
import java.util.Map;

public interface BulletinDao {

    //添加公告
    public boolean insertBulletin(Bulletin bulletin);

    //删除公告
    public boolean deleteBulletin(int id);

    //更新公告
    public boolean updateBulletin(Bulletin bulletin);

    //获取所有公告
    public List<Bulletin> queryAllBulletin();

    //通过 id 查询公告
    public List<Bulletin> queryBulletinById(int id);

    //分页查询
    public List<Bulletin> queryBulletinPage(Map<String,Object> data);

}
