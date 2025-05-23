package main.core.employee;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employeeDesk")
public class EmployeeDeskController {
    @GetMapping("/")
    public String showEmployeeDesk() {
        return "employee/desktop/employeeDesk";
    }
}
