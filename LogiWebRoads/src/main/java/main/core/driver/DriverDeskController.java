package main.core.driver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/driverDesk")
public class DriverDeskController {

    @GetMapping("/")
    public String getDriverDesk(){
        return "driver/driverDesk";
    }
}
