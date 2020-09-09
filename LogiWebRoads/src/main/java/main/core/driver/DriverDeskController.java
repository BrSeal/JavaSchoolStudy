package main.core.driver;

import main.core.driver.DTO.DriverDeskInfoDTO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/driverDesk")
public class DriverDeskController {

    private final DriverService driverService;

    public DriverDeskController(DriverService driverService) {
        this.driverService = driverService;
    }

    @GetMapping("/{username}")
    public String getDriverDesk(@PathVariable String username, Model model){
        DriverDeskInfoDTO driverDeskInfo=driverService.getDriverDeskInfo(username);
        model.addAttribute("dto",driverDeskInfo);

        return "driver/driverDesk";
    }
}
