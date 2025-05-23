package main.core.driver;

import main.core.driver.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public List<DriverMinInfoDTO> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public DriverInfoDTO getDriverById(@PathVariable int id) {
        return service.get(id);
    }

    @GetMapping("/assigned/{id}")
    public List<DriverInfoDTO> getDriversByOrderId(@PathVariable int id) {
        return service.getByOrderId(id);
    }

    @GetMapping("/available/{orderId}")
    public List<DriverInfoDTO> getAvailableVehicles(@PathVariable int orderId) {
        return service.getAvailable(orderId);
    }

    @PostMapping("/new/")
    public int saveDriver(@RequestBody NewDriverDTO dto) {
        return service.save(dto);
    }

    @PutMapping("/update/")
    public int updateDriver(@RequestBody DriverUpdateDTO dto) {
        return service.update(dto);
    }

    @PostMapping("/updateStatus/")
    public int updateDriver(@RequestBody UpdateStatusDriverDTO dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return service.update(dto, username);
    }

    @DeleteMapping("delete/{id}")
    public int deleteDriver(@PathVariable int id) {
        return service.delete(id);
    }
}