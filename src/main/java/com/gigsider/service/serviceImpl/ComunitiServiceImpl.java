package com.gigsider.service.serviceImpl;

import com.gigsider.dao.ComunitiDao;
import com.gigsider.po.Comuniti;
import com.gigsider.service.ComunitiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ComunitiServiceImpl implements ComunitiService {

    @Autowired
    ComunitiDao comunitiDao;

    @Override
    public boolean addComuniti(Comuniti comuniti) {
        return comunitiDao.insertComuniti(comuniti);
    }

    @Override
    public boolean delComuniti(int id) {
        return comunitiDao.deleteComuniti(id);
    }

    @Override
    public boolean upComuniti(Comuniti comuniti) {
        return comunitiDao.updateComuniti(comuniti);
    }

    @Override
    public List<Comuniti> getAllComuniti() {
        return comunitiDao.queryAllComuniti();
    }

    @Override
    public List<Comuniti> getComunitiPage(int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return comunitiDao.queryComunitiPage(data);
    }

    @Override
    public List<Comuniti> getComunitiById(int id) {
        return comunitiDao.queryComunitiById(id);
    }

    @Override
    public List<Comuniti> getComunitiLikePage(String like, int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("like", like);
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return comunitiDao.queryComunitiLikePage(data);
    }

    @Override
    public List<Comuniti> getComunitiLike(String like) {
        return comunitiDao.queryComunitiLike(like);
    }
}
