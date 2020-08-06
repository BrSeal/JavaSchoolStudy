package main.controllers.REST;

import main.model.logistic.Order;
import main.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService orderService) {
        this.service = orderService;
    }

    @GetMapping("/")
    @ResponseBody
    public List<Order> getDrivers() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public Order getDriverById(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/new/")
    @ResponseBody
    public int getOrderById(@RequestBody Order driver) {
        return service.save(driver);
    }

    @PutMapping("/update/")
    @ResponseBody
    public int updateOrderById(@RequestBody Order driverToUpdate) {
        return service.save(driverToUpdate);
    }

    @DeleteMapping("delete/{id}")
    @ResponseBody
    public Order deleteOrder(@PathVariable int id) {
        return service.delete(id);
    }
}
