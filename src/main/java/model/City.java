package model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@AllArgsConstructor
public class City {
    private int cityId;
    private String cityName;

    public static City create(ResultSet resultSet) throws SQLException {
        int cityId = resultSet.getInt("city_id");
        if (resultSet.wasNull()) {
            return null;
        }
        String cityName = resultSet.getString("city_name");
        return new City(cityId, cityName);
    }
}
