package main.core.employee;

import main.core.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;

    }

    @Override
    public List<Employee> getAll() {
        return repository.getAll();
    }

    @Override
    public Employee get(int id) {
        return repository.get(id);
    }

    @Override
    public int save(Employee e) {
        return repository.save(e);
    }

    @Override
    public Employee delete(int id) {
        return repository.delete(repository.get(id));
    }

    @Override
    public Employee delete(Employee employee) {
        return repository.delete(employee);
    }

    @Override
    public void update(Employee employee) {
        repository.update(employee);
    }
}
