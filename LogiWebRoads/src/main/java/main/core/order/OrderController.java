package main.core.order;

import main.core.order.DTO.AssignDriversOrderDTO;
import main.core.order.DTO.AssignVehicleOrderDTO;
import main.core.order.DTO.NewOrderDTO;
import main.core.order.DTO.OrderDTO;
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
    public List<OrderDTO> getOrders() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public OrderDTO getOrderById(@PathVariable int id) {
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
