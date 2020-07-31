package main.services;

import main.model.users.Employee;
import main.repositories.DriverRepository;
import main.repositories.EmployeeRepository;
import main.repositories.OrderRepository;
import main.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;



    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;

    }

    @Override
    public List<Employee> getAll() {
        return employeeRepository.getAll();
    }

    @Override
    public Employee get(int id) {
        return employeeRepository.get(id);
    }

    @Override
    public int save(Employee e) {
        return employeeRepository.save(e);
    }

    @Override
    public Employee delete(int id) {
        return employeeRepository.delete(id);
    }

    @Override
    public Employee delete(Employee employee) {
        return employeeRepository.delete(employee);
    }
}
