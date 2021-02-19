package com.gigsider.service.serviceImpl;

import com.gigsider.dao.MytestDao;
import com.gigsider.po.Mytest;
import com.gigsider.service.MytestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class MytestServiceImpl implements MytestService {

    @Autowired
    private MytestDao mytestDao;

    @Override
    public List<Mytest> findAll() {
        System.out.println("业务层，查询。。。。");
        return mytestDao.findAll();
    }

    @Override
    public void save(Mytest mytest) {
        mytestDao.save(mytest);
    }
}
