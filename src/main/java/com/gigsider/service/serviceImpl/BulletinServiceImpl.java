package com.gigsider.service.serviceImpl;

import com.gigsider.dao.BulletinDao;
import com.gigsider.po.Bulletin;
import com.gigsider.service.BulletinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BulletinServiceImpl implements BulletinService {

    @Autowired
    BulletinDao bulletinDao;

    @Override
    public boolean addBulletin(Bulletin bulletin) {
        return bulletinDao.insertBulletin(bulletin);
    }

    @Override
    public boolean delBulletin(int id) {
        return bulletinDao.deleteBulletin(id);
    }

    @Override
    public boolean upBulletin(Bulletin bulletin) {
        return bulletinDao.updateBulletin(bulletin);
    }

    @Override
    public List<Bulletin> getAllBulletin() {
        return bulletinDao.queryAllBulletin();
    }

    @Override
    public List<Bulletin> getBulletinPage(int page, int limit) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return bulletinDao.queryBulletinPage(data);
    }

    @Override
    public List<Bulletin> getBulletinById(int id) {
        return bulletinDao.queryBulletinById(id);
    }
}
