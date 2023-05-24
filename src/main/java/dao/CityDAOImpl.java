package dao;

import model.City;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDAOImpl implements CityDAO {
    private final Connection connection;

    public CityDAOImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public City findById(Integer cityId) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "Select * from city where city_id = (?)")) {
            preparedStatement.setInt(1, cityId);
            preparedStatement.setMaxRows(1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return City.create(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void createCity(String cityName) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO city (city_name) VALUES ((?))")) {
            preparedStatement.setString(1, cityName);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
