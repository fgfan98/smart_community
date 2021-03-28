package com.gigsider.dao;

import com.gigsider.po.Parking;
import com.sun.corba.se.spi.ior.ObjectKey;

import java.util.List;
import java.util.Map;

public interface ParkingDao {

    //添加车位
    public boolean insertParking(Parking parking);

    //通过主键 parking_id 删除车位
    public boolean deleteParking(String parking_id);

    //更新车位信息
    public boolean updateParking(Parking parking);

    //通过主键 parking_id 查找车位
    public List<Parking> queryParkingByParkingId(String parking_id);

    //通过车主车牌 license_num 查找车位
    public List<Parking> queryParkingByLicenseNum(String license_num);

    //获取所有车位
    public List<Parking> queryAllParking();

    //分页查询
    public List<Parking> queryParkingPage(Map<String, Object> data);

    //车位编号搜索的分页
    public List<Parking> queryParkingIdPage(Map<String, Object> data);

    //通过 status 查找车位
    public List<Parking> queryParkingByStatus(int status);

    // status 查找分页
    public List<Parking> statusParkingPage(Map<String,Object> data);

}
