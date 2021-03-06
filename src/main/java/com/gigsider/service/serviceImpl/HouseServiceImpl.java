package com.gigsider.service.serviceImpl;

import com.gigsider.dao.HouseDao;
import com.gigsider.po.House;
import com.gigsider.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean delHouse(String house_id) {
        return houseDao.deleteHouse(house_id);
    }

    @Override
    public boolean upHouse(House house) {
        return houseDao.updateHouse(house);
    }
}
