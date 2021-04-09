package com.gigsider.service.serviceImpl;

import com.gigsider.dao.FixDao;
import com.gigsider.po.Fix;
import com.gigsider.service.FixService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FixServiceImpl implements FixService {

    @Autowired
    private FixDao fixDao;

    @Override
    public boolean addFix(Fix fix) {
        return fixDao.insertFix(fix);
    }

    @Override
    public boolean delFix(int id) {
        return fixDao.deleteFix(id);
    }

    @Override
    public boolean upFix(Fix fix) {
        return fixDao.updateFix(fix);
    }

    @Override
    public List<Fix> getAllFix() {
        return fixDao.queryAllFix();
    }

    @Override
    public List<Fix> allFixPage(int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return fixDao.allFixPage(data);
    }

    @Override
    public List<Fix> getFixByUserName(String user_name) {
        return fixDao.queryFixByUserName(user_name);
    }

    @Override
    public List<Fix> userNameFixPage(String user_name, int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("user_name", user_name);
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return fixDao.userNameFixPage(data);
    }

    @Override
    public List<Fix> getFixLike(String like) {
        return fixDao.queryFixLike(like);
    }

    @Override
    public List<Fix> likeFixPage(String like, int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("like", like);
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return fixDao.likeFixPage(data);
    }

    @Override
    public List<Fix> getFixById(int id) {
        return fixDao.queryFixById(id);
    }
}
