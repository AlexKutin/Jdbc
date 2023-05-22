package dao;

import model.City;
import model.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    private final Connection connection;
    private final CityDAO cityDAO;

    public EmployeeDAOImpl(Connection connection) {
        this.connection = connection;
        cityDAO = new CityDAOImpl(connection);
    }

    @Override
    public Employee findById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "Select * from employee join city on employee.city_id = city.city_id and id = (?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.setMaxRows(1);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return Employee.create(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Employee> findAll() {
        List<Employee> employees = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "SELECT * FROM employee LEFT JOIN city c ON employee.city_id = c.city_id")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Employee employee = Employee.create(resultSet);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    @Override
    public int create(Employee employee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "INSERT INTO employee (first_name, last_name, gender, age, city_id) VALUES (?, ?, ?, ?, ?)")) {
            fillPrepareStatementFromEmployee(employee, preparedStatement);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public int updateById(Integer id, Employee newEmployee) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "UPDATE employee SET (first_name, last_name, gender, age, city_id) = (?, ?, ?, ?, ?) " +
                        "WHERE id = (?)")) {
            fillPrepareStatementFromEmployee(newEmployee, preparedStatement);
            preparedStatement.setInt(6, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    private void fillPrepareStatementFromEmployee(Employee newEmployee, PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.setString(1, newEmployee.getFirstName());
        preparedStatement.setString(2, newEmployee.getLastName());
        preparedStatement.setString(3, newEmployee.getGender());
        preparedStatement.setInt(4, newEmployee.getAge());
        City city = newEmployee.getCity();
        if (city == null) {
            preparedStatement.setNull(5, Types.INTEGER);
        } else {
            preparedStatement.setInt(5, city.getCityId());
            if (cityDAO.findById(city.getCityId()) == null) {
                cityDAO.createCity(city.getCityName());
            }
        }
    }

    @Override
    public int deleteById(Integer id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(
                "DELETE FROM employee WHERE id = (?)")) {
            preparedStatement.setInt(1, id);
            return preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
