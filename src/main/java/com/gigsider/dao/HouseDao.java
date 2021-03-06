package com.gigsider.dao;

import com.gigsider.po.House;

import java.util.List;
import java.util.Map;

public interface HouseDao {
    //添加住宅
    public boolean insertHouse(House house);

    //通过住宅编号 house_id 删除住宅
    public boolean deleteHouse(String house_id);

    //更新住宅信息
    public boolean updateHouse(House house);

    //通过住宅编号 house_id 查询住宅
    public List<House> queryHouseByHouseId(String house_id);

    //通过楼栋号 building_id 查询住宅
    public List<House> queryHouseByBuildingId(String building_id);

    //通过售出状态 sale 查询住宅
    public List<House> queryHouseBySale(int sale);

    //查询所有住宅
    public List<House> queryAllHouse();

    //分页查询
    public List<House> queryHousePage(Map<String, Object> data);
}
