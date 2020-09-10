package main.core.driver;

import main.core.driver.DTO.DriverDeskInfoDTO;
import main.core.orderManagement.cargo.DTO.UpdateStatusCargoDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @GetMapping("/")
    public String getDriverDesk(Model model){
        UpdateStatusCargoDTO cargoDto=new UpdateStatusCargoDTO();
        DriverDeskInfoDTO driverDeskInfo=driverService.getDriverDeskInfo();

        model.addAttribute("dto",driverDeskInfo);
        model.addAttribute("cargo",cargoDto);
        return "driver/driverDesk";
    }
}
