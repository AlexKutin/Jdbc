package dao;

import model.City;

public interface CityDAO {
    City findById(Integer cityId);

    Integer createByName(String cityName);
}
