package main.core.employee;

import main.core.employee.entity.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();

    Employee get(int id);

    int save(Employee e);

    void update(Employee e);

    Employee delete(Employee employee);
}
