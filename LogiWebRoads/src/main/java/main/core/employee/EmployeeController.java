package main.core.employee;

import main.core.employee.DTO.EmployeeDTO;
import main.core.employee.entity.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    @Autowired
    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/")
    public String listEmployees(Model model) {
        List<EmployeeDTO> employeeList = employeeService.getAll();
        model.addAttribute("employees", employeeList);
        return "employee/CRUD/employees";
    }

    @GetMapping("/create")
    public String createEmployee(Model model) {
        EmployeeDTO employee = new EmployeeDTO();
        model.addAttribute("employeeDTO", employee);

        return "employee/CRUD/employeeForm";
    }

    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute("employeeDTO") EmployeeDTO dto) {
        if (dto.getId() != 0) employeeService.update(dto.toEmployee());
        else employeeService.save(dto.toEmployee());
        return "redirect:/employees/";
    }

    @GetMapping("/update")
    public String updateEmployee(@RequestParam("employeeId") int id, Model model) {
        Employee employee = employeeService.get(id);
        model.addAttribute("employeeDTO", new EmployeeDTO(employee));

        return "employee/CRUD/employeeForm";
    }

    @GetMapping("/delete")
    public String deleteEmployee(@RequestParam("employeeId") int id) {
        employeeService.delete(id);
        return "redirect:/employees/";
    }
}
