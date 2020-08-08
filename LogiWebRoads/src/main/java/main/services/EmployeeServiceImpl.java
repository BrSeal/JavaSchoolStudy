package main.services;

import main.model.users.Employee;
import main.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
