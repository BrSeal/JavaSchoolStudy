package main.controllers;

import main.model.logistic.Order;
import main.model.logistic.Vehicle;
import main.model.users.Driver;
import main.model.users.Employee;
import main.services.DriverService;
import main.services.EmployeeService;
import main.services.OrderService;
import main.services.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;
    private final OrderService orderService;
    private final DriverService driverService;
    private final VehicleService vehicleService;

    @Autowired
    public EmployeeController(EmployeeService employeeService, OrderService orderService, DriverService driverService, VehicleService vehicleService) {
        this.employeeService = employeeService;
        this.orderService = orderService;
        this.driverService = driverService;
        this.vehicleService = vehicleService;
    }

    @GetMapping("/")
    public String listEmployees(Model model) {
        List<Employee> employeeList = employeeService.getAll();
        model.addAttribute("employees", employeeList);
        return "employee/CRUD/employees";
    }

    @GetMapping("/create")
    public String createEmployee(Model model) {
        Employee employee = new Employee();
        model.addAttribute("employee", employee);

        return "employee/CRUD/employeeForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
        employeeService.save(employee);
        return "redirect:/employees/";
    }

    //TODO сделать PUT-метод
    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
        Employee e = employeeService.get(id);
        model.addAttribute("employee", e);

        return "employee/CRUD/employeeForm";
    }

    //TODO сделать Delete-метод
    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.delete(id);
        return "redirect:/employees/";
    }

    @GetMapping("/employeeDesk")
    public String showEmployeeDesk(Model model) {
        List<Order> orders = orderService.getAll();
        List<Driver> drivers = driverService.getAll();
        List<Vehicle> vehicles = vehicleService.getAll();

        return "employee/desktop/employeeDesk";
    }
}
