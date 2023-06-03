import dao.CityDAO;
import dao.CityDAOImpl;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.City;
import model.Employee;

import java.util.List;

public class Application {

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
        CityDAO cityDAO = new CityDAOImpl();
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

        System.out.println("Update Employee with id = 3:");
        employeeId3.setAge(33);
        City cityBerlin = cityDAO.findById(6);
        employeeId3.setCity(cityBerlin);
        employeeDAO.update(employeeId3);
        System.out.println(employeeDAO.findById(3));
        System.out.println();

        // There is no employee with this id, the countRows should be 0
        System.out.println("Delete employee with id = 4:");
        int countRows = employeeDAO.deleteById(4);
        if (countRows == 0) {
            System.out.println("Nothing to delete");
        }
        System.out.println();

        System.out.println("Create new City:");
        City cityRio = new City();
        cityRio.setCityName("Rio de Janeiro");

        System.out.println("Create new Employee:");
        Employee employee = new Employee();
        employee.setFirstName("Thomas");
        employee.setLastName("Almeida");
        employee.setGender("Male");
        employee.setAge(31);
        employee.setCity(cityRio);

        employeeDAO.create(employee);
        List<Employee> employeeList = employeeDAO.findAll();
        System.out.println("Employee count: " + employeeList.size());
        employeeList.forEach(System.out::println);
        System.out.println();

        employeeDAO.delete(employee);
        System.out.println();
        employeeDAO.findAll().forEach(System.out::println);
    }
}
