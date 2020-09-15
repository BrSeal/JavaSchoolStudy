package main.core.employee;

import main.core.security.UserService;
import main.core.employee.DTO.EmployeeDTO;
import main.core.employee.entity.Employee;
import main.global.exceptionHandling.NullChecker;
import main.global.messaging.JMSProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;
    private final NullChecker nullChecker;
    private final UserService userService;


    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository, NullChecker nullChecker, UserService userService) {
        this.repository = repository;
        this.nullChecker = nullChecker;
        this.userService = userService;
    }

    @Override
    public List<EmployeeDTO> getAll() {
        return repository.getAll().stream()
                .map(EmployeeDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public Employee get(int id) {
        Employee employee = repository.get(id);
        nullChecker.throwNotFoundIfNull(employee, Employee.class, id);
        return employee;
    }

    @Override
    public int save(Employee employee) {

        userService.saveEmployee(employee.getUser());

        return repository.save(employee);
    }

    @Override
    public Employee delete(int id) {
        Employee employee=repository.get(id);
        nullChecker.throwNotFoundIfNull(employee,Employee.class,id);
        repository.delete(employee);
        userService.delete(employee.getUser());
        return employee;
    }

    @Override
    public void update(Employee employee) {
        repository.update(employee);
    }
}
