package main.core.orderManagement.order;

import main.core.orderManagement.order.DTO.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService orderService) {
        this.service = orderService;
    }

    @GetMapping("/")
    public List<SmallInfoOrderDTO> getOrders() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public InfoOrderDTO getOrderById(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/new/")
    public int getOrderById(@RequestBody NewOrderDTO o) {
       return service.save(o);
    }

    @PutMapping("/assignVehicle/")
    public void assignVehicle(@RequestBody AssignVehicleOrderDTO dto) {
         service.assignVehicle(dto);
    }

    @PutMapping("/assignDrivers/")
    public void assignDrivers(@RequestBody AssignDriversOrderDTO dto) {
        service.assignDrivers(dto);
    }
}
