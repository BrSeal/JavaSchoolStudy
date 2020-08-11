package main.controllers.REST;

import main.model.users.Driver;
import main.model.users.DriverDTO;
import main.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/driver")
public class DriverController {
    private final DriverService service;

    @Autowired
    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping("/")
    public List<Driver> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Driver getDriverById(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/new/")
    public String saveDriver( DriverDTO dto) {
        return service.save(dto);
    }

    @PostMapping("/update/")
    public String updateDriver(DriverDTO dto) {
        return service.update(dto);
    }

    @DeleteMapping("delete/{id}")
    public String deleteDriver(@PathVariable int id) {
        return service.delete(id);
    }
}
