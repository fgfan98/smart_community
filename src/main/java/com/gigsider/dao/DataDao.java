package com.gigsider.dao;

import com.gigsider.po.Data;

public interface DataDao {

    //获取数据
    public Data queryData(String key);

    //更新数据
    public boolean updateData(Data data);

}
