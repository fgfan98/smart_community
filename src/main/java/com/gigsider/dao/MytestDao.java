package com.gigsider.dao;

import com.gigsider.po.Mytest;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface MytestDao {

    @Select(value = "select * from mytest")
    public List<Mytest> findAll();

    @Insert(value = "insert into mytest (string) values (#{string})")
    public void save(Mytest mytest);

}
