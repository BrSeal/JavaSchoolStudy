package main.core.order;

import main.model.logistic.orderAndWaypoint.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<OrderDTO> getAll() {
        return repository.getAll().stream().map(OrderDTO::new).collect(Collectors.toList());
    }

    @Override
    public OrderDTO get(int id) {
        return new OrderDTO(repository.get(id));
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
