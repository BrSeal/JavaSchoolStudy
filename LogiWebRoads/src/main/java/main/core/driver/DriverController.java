package main.core.driver;

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
    public String saveDriver(DriverDTO dto) {
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
