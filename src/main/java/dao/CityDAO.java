package dao;

import model.City;

public interface CityDAO {
    City findById(Integer cityId);

    void create(City city);

    Integer createByName(String cityName);

    void update(City city);

    void delete(City city);
}
