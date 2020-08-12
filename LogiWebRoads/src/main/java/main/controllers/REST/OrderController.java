package main.controllers.REST;

import main.model.logistic.Order;
import main.model.logistic.OrderDTO;
import main.services.OrderService;
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
    public List<Order> getOrders() {
        return service.getAll();
    }

    @GetMapping("/get/{id}")
    public Order getOrderById(@PathVariable int id) {
        return service.get(id);
    }

    @PostMapping("/new/")
    public int getOrderById(OrderDTO o) {
        return service.save(o);
    }

    @PutMapping("/update/")
    public void updateOrderById(OrderDTO orderToUpdate) {
         service.update(orderToUpdate);
    }
}
