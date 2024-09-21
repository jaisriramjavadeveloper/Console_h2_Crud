package com.test.model.repo;


import java.util.List;

import com.test.model.Employee;

public interface EmployeeRepository {
    void save(Employee employee);
    Employee findById(int empId);
    List<Employee> findAll();
    void update(Employee employee);
    void delete(int empId);
}