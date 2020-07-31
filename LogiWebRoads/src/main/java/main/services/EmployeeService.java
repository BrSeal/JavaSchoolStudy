package main.services;

import main.model.users.Employee;

import java.util.List;
import java.util.Map;

public interface EmployeeService {
    List<Employee> getAll();

    Employee get(int id);

    int save(Employee e);

    Employee delete(int id);

    Employee delete(Employee employee);
}
