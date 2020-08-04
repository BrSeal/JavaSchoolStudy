package main.controllers;

import main.model.users.Driver;
import main.services.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/driver")
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
    public int getDriverById(@RequestBody Driver driver) {
        return service.save(driver);
    }

    @PutMapping("/update/")
    @ResponseBody
    public int updateDriverById(@RequestBody Driver driverToUpdate) {
        return service.save(driverToUpdate);
    }

    @DeleteMapping("delete/{id}")
    public Driver deleteDriver(@PathVariable int id) {
        return service.delete(id);
    }
}
