package com.test.app;
import java.util.List;

import com.test.model.*;
import com.test.model.repo.*;

public class TestCrud {

	public static void main(String[] args) {
		EmployeeRepository employeeRepo = new EmployeeRepositoryImpl();

        // 1. Insert Employees
        employeeRepo.save(new Employee(1, "John", "USA"));
        employeeRepo.save(new Employee(2, "Maria", "Canada"));
        employeeRepo.save(new Employee(3, "Raj", "India"));

        // 2. Retrieve All Employees
        List<Employee> employees = employeeRepo.findAll();
        System.out.println("All Employees:");
        employees.forEach(System.out::println);

        // 3. Update Employee
        Employee updatedEmployee = new Employee(2, "Maria Doe", "Canada");
        employeeRepo.update(updatedEmployee);

        // 4. Retrieve Employee by ID
        Employee employee = employeeRepo.findById(2);
        System.out.println("\nUpdated Employee with ID 2:");
        System.out.println(employee);

        // 5. Delete Employee
        employeeRepo.delete(1);
        System.out.println("\nEmployee with ID 1 deleted.");

        // 6. Retrieve All Employees again
        employees = employeeRepo.findAll();
        System.out.println("\nAll Employees after deletion:");
        employees.forEach(System.out::println);

	}

}
