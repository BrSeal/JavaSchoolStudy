package main.services;

import main.model.logistic.Order;
import main.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;

    @Autowired
    public OrderServiceImpl(OrderRepository repository) {
        this.repository = repository;
    }

    @Override
    public int save(Order order) {
        return repository.save(order);
    }

    @Override
    public List<Order> getAll() {
        return repository.getAll();
    }

    @Override
    public Order get(int it) {
        return null;
    }

    @Override
    public Order delete(int id) {
        return null;
    }

    @Override
    public Order delete(Order waypoint) {
        return null;
    }
}