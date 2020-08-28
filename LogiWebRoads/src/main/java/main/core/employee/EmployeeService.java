package main.core.employee;

import main.core.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<Employee> getAll();

    Employee get(int id);

    int save(Employee e);

    void update(Employee e);

    Employee delete(int id);

    Employee delete(Employee employee);
}
