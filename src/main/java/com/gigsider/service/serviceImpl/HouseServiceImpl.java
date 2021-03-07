package com.gigsider.service.serviceImpl;

import com.gigsider.dao.HouseDao;
import com.gigsider.po.House;
import com.gigsider.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class HouseServiceImpl implements HouseService {

    @Autowired
    HouseDao houseDao;

    @Override
    public List<House> getAllHouse() {
        return houseDao.queryAllHouse();
    }

    @Override
    public List<House> getHouseByHouseId(String house_id) {
        return houseDao.queryHouseByHouseId(house_id);
    }

    @Override
    public List<House> getHouseByBuildingId(String building_id) {
        return houseDao.queryHouseByBuildingId(building_id);
    }

    @Override
    public List<House> getHouseBySale(int sale) {
        return houseDao.queryHouseBySale(sale);
    }

    @Override
    public List<House> getUnSoldHouse() {
        return houseDao.queryHouseBySale(0);
    }

    @Override
    public boolean addHouse(House house) {
        return houseDao.insertHouse(house);
    }

    @Override
    public List<House> getHouseIdPage(int page, int limit, String house_id) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit",limit);
        data.put("house_id",house_id);
        return houseDao.queryHouseIdPage(data);
    }

    @Override
    public List<House> getHousePage(int page, int limit) {
        Map<String, Object> data = new HashMap<>();
        data.put("page", (page-1)*limit);
        data.put("limit", limit);
        return houseDao.queryHousePage(data);
    }

    @Override
    public boolean delHouse(String house_id) {
        return houseDao.deleteHouse(house_id);
    }

    @Override
    public boolean upHouse(House house) {
        return houseDao.updateHouse(house);
    }
}
