package main.services;

import main.model.users.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee getById(int id);

    int save(Employee e);
}
