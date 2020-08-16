package main.core.driver;

import main.core.driver.DTO.DriverDTO;
import main.core.driver.DTO.DriverDeskInfoDTO;
import main.core.driver.DTO.DriverInfoDTO;
import main.core.driver.DTO.NewDriverDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverCRUDController {
    private final DriverService service;

    @Autowired
    public DriverCRUDController(DriverService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<DriverInfoDTO> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DriverDTO getDriverById(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping("/assigned/{id}")
    public List<DriverInfoDTO> getDriversByOrderId(@PathVariable int id) {
        return service.getByOrderId(id);
    }

    @PostMapping("/new/")
    public int saveDriver(@RequestBody NewDriverDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update/")
    public int updateDriver(@RequestBody DriverInfoDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public int deleteDriver(@PathVariable int id) {
        return service.delete(id);
    }

    @GetMapping("/available/{orderId}")
    public List<DriverInfoDTO> getAvailableVehicles(@PathVariable int orderId){
        return service.getAvailable(orderId);
    }

    @GetMapping("/info/{id}")
    public DriverDeskInfoDTO getDriverDeskInfo(@PathVariable int id){
        return service.getDriverDeskInfo(id);
    }
}