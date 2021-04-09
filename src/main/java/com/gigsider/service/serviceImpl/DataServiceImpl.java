package com.gigsider.service.serviceImpl;

import com.gigsider.dao.DataDao;
import com.gigsider.po.Data;
import com.gigsider.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataServiceImpl implements DataService {

    @Autowired
    private DataDao dataDao;

    @Override
    public Data getData(String key) {
        return dataDao.queryData(key);
    }

    @Override
    public boolean upData(Data data) {
        return dataDao.updateData(data);
    }
}
