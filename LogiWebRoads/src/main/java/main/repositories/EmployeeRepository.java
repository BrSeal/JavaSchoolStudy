package main.repositories;

import main.model.users.Employee;

import java.util.List;

public interface EmployeeRepository {
    List<Employee> getAll();

    Employee getById(int id);

    int save(Employee e);

    boolean edit(int id,Employee changes);

    Employee deleteById(int id);
}
