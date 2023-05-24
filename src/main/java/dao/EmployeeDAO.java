package dao;

import model.Employee;

import java.util.List;

public interface EmployeeDAO {
    Employee findById(Integer id);

    List<Employee> findAll();

    void create(Employee employee);

    void update(Employee employee);

    int deleteById(Integer id);

    void delete(Employee employee);
}
