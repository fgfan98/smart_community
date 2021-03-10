package com.gigsider.service;

import com.gigsider.po.Bulletin;

import java.util.List;

public interface BulletinService {

    //添加公告
    public boolean addBulletin(Bulletin bulletin);

    //删除公告
    public boolean delBulletin(int id);

    //更新公告
    public boolean upBulletin(Bulletin bulletin);

    //获取所有公告
    public List<Bulletin> getAllBulletin();

    //通过 id 查询公告
    public List<Bulletin> getBulletinById(int id);

    //分页查询
    public List<Bulletin> getBulletinPage(int page, int limit);

}
