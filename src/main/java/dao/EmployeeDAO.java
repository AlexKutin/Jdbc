package dao;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee findById(Integer id);

    List<Employee> findAll();

    int create(Employee employee);

    int updateById(Integer id, Employee newEmployee);

    int deleteById(Integer id);
}
