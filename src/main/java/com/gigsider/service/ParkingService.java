package com.gigsider.service;

import com.gigsider.po.Parking;

import java.util.List;

public interface ParkingService {

    //添加车位
    public boolean addParking(Parking parking);

    //通过主键 parking_id 删除车位
    public boolean delParking(String parking_id);

    //更新车位信息
    public boolean upParking(Parking parking);

    //通过 parking_id 查找车位
    public List<Parking> getParkingByParkingId(String parking_id);

    //通过 license_num 查找车位
    public List<Parking> getParkingByLicenseNum(String license_num);

    //获取所有车位
    public List<Parking> getAllParking();

    //分页查询
    public List<Parking> getParkingPage(int page, int limit);

    //车位编号搜索的分页
    public List<Parking> getParkingIdPage(int page, int limit, String parking_id);

    //通过 status 查找车位
    public List<Parking> getParkingByStatus(int status);

    // status 查找分页
    public List<Parking> statusParkingPage(int status, int page, int limit);

    //获取已租赁车位
    public List<Parking> getRentParking();

}
