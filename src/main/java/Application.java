import config.ConnectionConfig;
import dao.CityDAO;
import dao.CityDAOImpl;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.City;
import model.Employee;

import java.sql.*;

public class Application {

    public static void main(String[] args) {
        final String getByIdRequest = "SELECT * FROM employee WHERE id = (?)";

        try (Connection connection = ConnectionConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(getByIdRequest)
        ) {
            int idEmployee = 2;
            preparedStatement.setInt(1, idEmployee);
            preparedStatement.executeQuery();
            ResultSet resultSet = preparedStatement.getResultSet();
            if (resultSet.next()) {
                System.out.printf("Employee{id: %d, firstName: %s, lastName: %s, gender: %s, age: %d, cityId: %d}\n",
                        resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name"),
                        resultSet.getString("gender"),
                        resultSet.getInt("age"),
                        resultSet.getInt("city_id")
                        );
            } else {
                System.out.printf("Employee with id: %d not found!\n", idEmployee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Task 2
        try (Connection connection = ConnectionConfig.getConnection();
        ) {
            EmployeeDAO employeeDAO = new EmployeeDAOImpl(connection);
            System.out.println("Print all employees:");
            employeeDAO.findAll().forEach(System.out::println);
            System.out.println();

            // There is no employee with this id, the result should be null
            System.out.println("Print employee with id = 4:");
            System.out.println(employeeDAO.findById(4));

            // Employee with this id is present in the table
            System.out.println("Print employee with id = 3:");
            Employee employeeId3 = employeeDAO.findById(3);
            System.out.println(employeeId3);
            System.out.println();

            System.out.println(employeeDAO.findById(7));

            System.out.println("Update Employee with id = 3:");
            CityDAO cityDAO = new CityDAOImpl(connection);
            employeeId3.setAge(19);
            employeeId3.setCity(cityDAO.findById(5));   // Mexico
            if (employeeDAO.updateById(3, employeeId3) > 0) {
                System.out.println("Update successfully!");
                System.out.println(employeeDAO.findById(3));
            }
            System.out.println();

            // There is no employee with this id, the countRows should be 0
            System.out.println("Delete employee with id = 4:");
            int countRows = employeeDAO.deleteById(4);
            if (countRows == 0) {
                System.out.println("Nothing to delete");
            }
            System.out.println();

            System.out.println("Create new Employee:");
            City city = cityDAO.findById(7);  // New York

            Employee employee = new Employee();
            employee.setFirstName("Mark");
            employee.setLastName("Nelson");
            employee.setGender("Male");
            employee.setAge(39);
            employee.setCity(city);
            System.out.println("\t" + employee);

            if (employeeDAO.create(employee) > 0) {
                System.out.println("Employee added successfully!");
                System.out.println("Employee count: " + employeeDAO.findAll().size());
            }
            System.out.println();

            System.out.println("Delete employee with id = 8:");
            if (employeeDAO.deleteById(8) > 0) {
                System.out.println("Delete successfully!");
            } else {
                System.out.println("Employee with this id is missing in the table");
            }
            System.out.println();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
