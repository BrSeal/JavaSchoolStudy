package main.repositories;

import main.model.users.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();

    Employee get(int id);

    int save(Employee e);

    Employee delete(int id);

    Employee delete(Employee employee);
}
