package main.controllers.REST;

import main.model.users.Driver;
import main.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/driver")
public class DriverController {
    private final DriverService service;

    @Autowired
    public DriverController(DriverService service) {
        this.service = service;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Driver> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Driver getDriverById(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/new/")
    @ResponseBody
    public int saveDriver(@RequestBody Driver d) {
        return service.save(d);
    }

    @PutMapping("/update/")
    @ResponseBody
    public int updateDriverById(@RequestBody Driver driverToUpdate) {
        return service.save(driverToUpdate);
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public Driver deleteDriver(@PathVariable int id) {
        return service.delete(id);
    }

    @DeleteMapping("delete/")
    @ResponseBody
    public Driver deleteDriver(@RequestBody Driver d) {
        return service.delete(d);
    }
}
