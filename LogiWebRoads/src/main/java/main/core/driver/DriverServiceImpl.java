package main.core.driver;

import main.core.driver.DTO.DriverDTO;
import main.core.driver.DTO.DriverDeskInfoDTO;
import main.core.driver.DTO.DriverInfoDTO;
import main.core.order.OrderRepository;
import main.model.logistic.City;
import main.model.logistic.Order;
import main.model.users.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static main.core.driver.services.DriverCheck.canBeDeleted;
import static main.core.order.services.OrderCalculator.calculateOrderWorkTimeFirstMonth;
import static main.core.order.services.OrderUpdateChecker.isVehicleAssigned;

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
    public List<DriverInfoDTO> getAll() {
        return driverRepository.getAll().stream()
                .map(DriverInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DriverInfoDTO get(int id) {
        return new DriverInfoDTO(driverRepository.get(id));
    }

    @Override
    public List<DriverInfoDTO> getByOrderId(int orderId) {
        String hql="from Driver d where d.currentOrder= "+orderId;
        return driverRepository.getByQuery(hql).stream()
                .map(DriverInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverInfoDTO> getAvailable(int orderId) {
        Order order=orderRepository.get(orderId);
        if(!isVehicleAssigned(order)) throw new IllegalArgumentException("Vehicle is not assigned!");
        int dutySize = order.getAssignedVehicle().getDutySize();
        int hoursPerWorker=(int)Math.ceil((double) calculateOrderWorkTimeFirstMonth(order)/dutySize);
        City city=order.getAssignedVehicle().getCurrentCity();


        return driverRepository.getAvailable(hoursPerWorker,city).stream()
                .map(DriverInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDeskInfoDTO getDriverDeskInfo(int id) {
        Driver driver=driverRepository.get(id);
        return new DriverDeskInfoDTO(driver);
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
    public int update(DriverInfoDTO dto) {
        driverRepository.update(dto.toDriver());
        return dto.getId();
    }
}
