package com.gigsider.service;

import com.gigsider.po.Data;

public interface DataService {

    //获取数据
    public Data getData(String key);

    //更新数据
    public boolean upData(Data data);

}
