package main.services;

import main.model.users.Employee;
import main.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
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
    public Employee getById(int id) {
        return repository.getById(id);
    }

    @Override
    public int save(Employee e) {
        return repository.save(e);
    }
}
