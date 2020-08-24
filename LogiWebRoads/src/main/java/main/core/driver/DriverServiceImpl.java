package main.core.driver;

import main.core.driver.DTO.DriverDTO;
import main.core.driver.DTO.DriverDeskInfoDTO;
import main.core.driver.DTO.DriverInfoDTO;
import main.core.driver.DTO.UpdateStatusDriverDTO;
import main.core.driver.services.DriverCheckProvider;
import main.core.driver.services.DriverLogic;
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

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository driverRepository;
    private final OrderRepository orderRepository;
    private final DriverCheckProvider checkProvider;
    private final DriverLogic driverLogic;


    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, OrderRepository orderRepository, DriverCheckProvider checkProvider, DriverLogic driverLogic) {
        this.driverRepository = driverRepository;
        this.orderRepository = orderRepository;
        this.checkProvider = checkProvider;
        this.driverLogic = driverLogic;
    }

    @Override
    public List<DriverInfoDTO> getAll() {
        return driverRepository.getAll().stream()
                .map(DriverInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DriverInfoDTO get(int id) {
        Driver d=driverRepository.get(id);
        checkProvider.exists(d);
        return new DriverInfoDTO(d);
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

        int hoursPerWorker =driverLogic.getHoursPerWorker(order);

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
        checkProvider.canBeDeleted(driver);

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

        driverLogic.updateStatus(order,driver,status);
        driverRepository.update(driver);
        return dto.toDriver().getId();
    }
}
