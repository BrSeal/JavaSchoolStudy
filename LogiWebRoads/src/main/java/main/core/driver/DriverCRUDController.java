package main.core.driver;

import main.core.driver.DTO.DriverDTO;
import main.core.vehicle.VehicleDTO;
import main.model.users.Driver;
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
    public List<DriverDTO> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DriverDTO getDriverById(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping("/assigned/{id}")
    public List<DriverDTO> getDriversByOrderId(@PathVariable int id) {
        return service.getByOrderId(id);
    }

    @PostMapping("/new/")
    public int saveDriver(@RequestBody DriverDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update/")
    public int updateDriver(@RequestBody DriverDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public int deleteDriver(@PathVariable int id) {
        return service.delete(id);
    }

    @GetMapping("/available/{orderId}")
    public List<DriverDTO> getAvailableVehicles(@PathVariable int orderId){
        return service.getAvailable(orderId);
    }
}
