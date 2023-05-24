package dao;

import model.City;

public interface CityDAO {
    City findById(Integer cityId);

    void createCity(String cityName);
}
