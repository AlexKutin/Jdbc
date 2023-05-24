package model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.ResultSet;
import java.sql.SQLException;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    private int id;
    private String firstName;
    private String lastName;
    private String gender;
    private Integer age;
    private City city;

    public static Employee create(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String first_name = resultSet.getString("first_name");
        String last_name = resultSet.getString("last_name");
        String gender = resultSet.getString("gender");
        int age = resultSet.getInt("age");
        City city = City.create(resultSet);
        return new Employee(id, first_name, last_name, gender, age, city);
    }
}
