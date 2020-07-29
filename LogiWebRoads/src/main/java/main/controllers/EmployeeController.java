package main.controllers;

import main.model.users.Employee;
import main.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService service;

    @Autowired
    public EmployeeController(EmployeeService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String listEmployees(Model model) {
        List<Employee> employeeList = service.getAll();
        model.addAttribute("employees", employeeList);
        return "employees";
    }

    @GetMapping("/create")
    public String createEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employeeForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        service.save(employee);
        return "redirect:/employees/";
    }

    //TODO сделать PUT-метод
    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
        Employee e = service.getById(id);
        model.addAttribute("employee", e);

        return "employeeForm";
    }


    //TODO сделать Delete-метод
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id){

         service.deleteById(id);

        return "redirect:/employees/";
    }

}
