package com.gigsider.service.serviceImpl;

import com.gigsider.dao.ParkingDao;
import com.gigsider.po.Parking;
import com.gigsider.service.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ParkingServiceImpl implements ParkingService {

    @Autowired
    ParkingDao parkingDao;

    @Override
    public boolean addParking(Parking parking) {
        return parkingDao.insertParking(parking);
    }

    @Override
    public boolean delParking(String parking_id) {
        return parkingDao.deleteParking(parking_id);
    }

    @Override
    public boolean upParking(Parking parking) {
        return parkingDao.updateParking(parking);
    }

    @Override
    public List<Parking> getParkingByParkingId(String parking_id) {
        return parkingDao.queryParkingByParkingId(parking_id);
    }

    @Override
    public List<Parking> getParkingByLicenseNum(String license_num) {
        return parkingDao.queryParkingByLicenseNum(license_num);
    }

    @Override
    public List<Parking> getAllParking() {
        return parkingDao.queryAllParking();
    }

    @Override
    public List<Parking> getParkingPage(int page, int limit) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return parkingDao.queryParkingPage(data);
    }

    @Override
    public List<Parking> getParkingIdPage(int page, int limit, String parking_id) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        data.put("parking_id", parking_id);
        return parkingDao.queryParkingIdPage(data);
    }

    @Override
    public List<Parking> getParkingByStatus(int status) {
        return parkingDao.queryParkingByStatus(status);
    }

    @Override
    public List<Parking> statusParkingPage(int status, int page, int limit) {
        Map<String,Object> data = new HashMap<>();
        data.put("status", status);
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return parkingDao.statusParkingPage(data);
    }

    @Override
    public List<Parking> getRentParking() {
        return parkingDao.queryRentParking();
    }
}
