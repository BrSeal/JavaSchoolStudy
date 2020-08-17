package main.core.driver;

import main.core.driver.DTO.DriverDTO;
import main.core.driver.DTO.DriverDeskInfoDTO;
import main.core.driver.DTO.DriverInfoDTO;
import main.core.driver.DTO.UpdateStatusDriverDTO;
import main.core.order.OrderRepository;
import main.model.logistic.City;
import main.model.logistic.Order;
import main.model.users.Driver;
import main.model.users.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static main.core.driver.services.DriverCheckProvider.canBeDeleted;
import static main.core.driver.services.DriverLogic.getHoursPerWorker;
import static main.core.driver.services.DriverLogic.updateStatus;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

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
        String hql = "from Driver d where d.currentOrder= " + orderId;
        return driverRepository.getByQuery(hql).stream()
                .map(DriverInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverInfoDTO> getAvailable(int orderId) {
        Order order = orderRepository.get(orderId);

        int hoursPerWorker =getHoursPerWorker(order);

        City city = order.getAssignedVehicle().getCurrentCity();


        return driverRepository.getAvailable(hoursPerWorker, city).stream()
                .map(DriverInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDeskInfoDTO getDriverDeskInfo(int id) {
        Driver driver = driverRepository.get(id);
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
        canBeDeleted(driver);

        driverRepository.delete(driver);
        return driver.getId();
    }

    @Override
    public int update(DriverInfoDTO dto) {
        driverRepository.update(dto.toDriver());
        return dto.getId();
    }

    @Override
    public int update(UpdateStatusDriverDTO dto) {
        DriverStatus status=dto.toDriver().getStatus();
        Driver driver=driverRepository.get(dto.toDriver().getId());
        Order order=orderRepository.get(driver.getCurrentOrder().getId());

        updateStatus(order,driver,status);
        driverRepository.update(driver);
        return dto.toDriver().getId();
    }
}
