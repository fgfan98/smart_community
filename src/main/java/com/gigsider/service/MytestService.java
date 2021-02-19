package com.gigsider.service;

import com.gigsider.po.Mytest;

import java.util.List;

public interface MytestService {

    public List<Mytest> findAll();
    public void save(Mytest mytest);

}
