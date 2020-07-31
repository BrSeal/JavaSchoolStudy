package main.controllers;

import main.model.logistic.Order;
import main.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/order")
public class OrderController {
    private final OrderService service;

    @Autowired
    public OrderController(OrderService orderService) {
        this.service = orderService;
    }


    @PostMapping("/save")
    public String saveOrder(@RequestParam Order order){
        service.save(order);
        return "redirect:/employees/employeeDesk";
    }

    @PostMapping("/update")
    public String updateOrder(@RequestParam Order order) {
        service.save(order);
        return "redirect:/employees/employeeDesk";
    }
    @GetMapping("/new")
    public String newOrder() {
        return "/order/orderForm";
    }
}
