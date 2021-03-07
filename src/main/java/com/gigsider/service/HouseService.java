package com.gigsider.service;

import com.gigsider.po.House;

import java.util.List;

public interface HouseService {
    //获取所有住宅信息
    public List<House> getAllHouse();

    //通过住宅编号 house_id 查询住宅
    public List<House> getHouseByHouseId(String house_id);

    //通过楼栋号 building_id 查询住宅
    public List<House> getHouseByBuildingId(String building_id);

    //通过售出状态 sale 查询住宅
    public List<House> getHouseBySale(int sale);

    //查找未售出的住宅
    public List<House> getUnSoldHouse();

    //分页查找住宅信息
    public List<House> getHousePage(int page, int limit);

    //获取住宅编号查找分页信息
    public List<House> getHouseIdPage(int page, int limit, String house_id);

    //添加住宅
    public boolean addHouse(House house);

    //通过住宅编号 house_id 删除住宅
    public boolean delHouse(String house_id);

    //更新住宅信息
    public boolean upHouse(House house);
}
