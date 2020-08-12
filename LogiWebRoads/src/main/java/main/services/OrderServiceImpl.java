package main.services;

import main.model.logistic.Order;
import main.model.logistic.OrderDTO;
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
    public int save(OrderDTO order) {
        return repository.save(order.toOrder());
    }

    @Override
    public List<Order> getAll() {
        return repository.getAll();
    }

    @Override
    public Order get(int id) {
        return repository.get(id);
    }

    @Override
    public Order delete(int id) {
        return repository.delete(repository.get(id));
    }

    @Override
    public Order delete(Order order) {
        return null;
    }

    @Override
    public void update(OrderDTO dto) {
        repository.update(dto.toOrder());
    }
}
