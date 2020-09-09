package main.core.employee;

import main.core.employee.DTO.EmployeeDTO;
import main.core.employee.entity.Employee;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDTO> getAll();

    Employee get(int id);

    int save(Employee e);

    void update(Employee e);

    Employee delete(int id);
}
