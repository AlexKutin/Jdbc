import dao.CityDAO;
import dao.CityDAOImpl;
import dao.EmployeeDAO;
import dao.EmployeeDAOImpl;
import model.City;
import model.Employee;

public class Application {

    public static void main(String[] args) {
        EmployeeDAO employeeDAO = new EmployeeDAOImpl();
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
        employeeId3.setAge(49);
        employeeId3.setCity(7);   // New York
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
        CityDAO cityDAO = new CityDAOImpl();
        int cityId = cityDAO.createByName("Rio de Janeiro");
        City city = cityDAO.findById(cityId);
        System.out.println("New city: " + city);

        System.out.println("Create new Employee:");
        Employee employee = new Employee();
        employee.setFirstName("Thomas");
        employee.setLastName("Almeida");
        employee.setGender("Male");
        employee.setAge(31);
        employee.setCity(cityId);
        System.out.println("\t" + employee);

        employeeDAO.create(employee);
        System.out.println("Employee count: " + employeeDAO.findAll().size());
        System.out.println();

        System.out.println("Delete employee with id = 10:");
        if (employeeDAO.deleteById(10) > 0) {
            System.out.println("Delete successfully!");
        } else {
            System.out.println("Employee with this id is missing in the table");
        }
        System.out.println();
    }
}
