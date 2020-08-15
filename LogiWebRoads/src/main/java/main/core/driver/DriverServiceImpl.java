package main.core.driver;

import main.core.driver.DTO.DriverDTO;
import main.core.order.OrderRepository;
import main.model.logistic.Order;
import main.model.users.Driver;
import main.model.users.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private static final String DELETE_FAIL = "Deletion failed! Driver #%d is currently on order #%d!";

    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, OrderRepository orderRepository) {
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public List<DriverDTO> getAll() {
        return driverRepository.getAll().stream()
                .map(DriverDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDTO get(int id) {
        return new DriverDTO(driverRepository.get(id));
    }

    @Override
    public List<DriverDTO> getByOrderId(int orderId) {
        return driverRepository.getByOrderId(orderId).stream()
                .map(DriverDTO::new)
                .collect(Collectors.toList());
    }


    //TODO посчитать продолжительность заказа
    @Override
    public List<DriverDTO> getAvailable(int orderId) {
        Order order=orderRepository.get(orderId);



        return null;
    }

    @Override
    public int save(DriverDTO dto) {
        return driverRepository.save(dto.toDriver());
    }

    @Override
    public int delete(int id) {
        return delete(driverRepository.get(id));
    }

    @Override
    public int delete(Driver driver) {
        if (!canBeDeleted(driver)){
            int id= driver.getId();
            int orderId= driver.getCurrentOrder().getId();
            throw
                    new IllegalArgumentException(String.format(DELETE_FAIL,id, orderId));
        }

        driverRepository.delete(driver);
        return driver.getId();
    }

    @Override
    public int update(DriverDTO dto) {
        driverRepository.update(dto.toDriver());
        return dto.getId();
    }

    private boolean canBeDeleted(Driver d) {
        return d.getCurrentOrder() == null && d.getStatus().equals(DriverStatus.ON_REST);
    }

    private int calculate
}
