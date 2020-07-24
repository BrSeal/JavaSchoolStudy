package main.controller;

import main.model.users.Employee;
import main.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    //get employee list
    @GetMapping("/")
    public String listEmployees(Model model) {
        List<Employee> employeeList= employeeRepository.getAll();
        model.addAttribute("employees",employeeList);
        return "employees";
    }

    //save new Employee
    @PostMapping("/add")
    public String addEmployee(@RequestBody Employee employee) {
        return null;
    }

    //edit employee
    @PutMapping("/edit/{id}")
    public int editEmployeeById(@PathVariable int id) {
        return 0;
    }

    //delete employee
    @PutMapping("/delete/{id}")
    public int removeEmployeeById(@PathVariable int id) {
        return 0;
    }

    //get employee
    @GetMapping("/{id}")
    public Employee getEmployeeById(@PathVariable int id) {
        return null;
    }
}
