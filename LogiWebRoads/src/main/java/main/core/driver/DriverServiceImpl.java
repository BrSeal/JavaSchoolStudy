package main.core.driver;

import main.core.Security.UserService;
import main.core.Security.entity.User;
import main.core.cityAndRoads.cities.entity.City;
import main.core.driver.DTO.*;
import main.core.driver.entity.Driver;
import main.core.driver.entity.DriverStatus;
import main.core.driver.services.DriverCheckProvider;
import main.core.driver.services.DriverLogic;
import main.core.orderManagement.order.OrderRepository;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.services.OrderLogic;
import main.core.vehicle.entity.Vehicle;
import main.global.exceptionHandling.NullChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private static final int WORKING_HOURS_PER_MONTH = 176;
    private static final String ORDER_IS_NULL = "Update failed!";
    private static final String NO_DRIVER_FOR_USER = "No drivers found associated with user %s";

    private final DriverRepository driverRepository;
    private final DriverCheckProvider checkProvider;
    private final UserService userService;
    private final DriverLogic driverLogic;
    private final OrderRepository orderRepository;
    private final NullChecker nullChecker;
    private final OrderLogic orderLogic;


    @Autowired
    public DriverServiceImpl(DriverRepository driverRepository, UserService userService, OrderRepository orderRepository, DriverCheckProvider checkProvider, DriverLogic driverLogic, NullChecker nullChecker, OrderLogic orderLogic) {
        this.driverRepository = driverRepository;
        this.userService = userService;
        this.orderRepository = orderRepository;
        this.checkProvider = checkProvider;
        this.driverLogic = driverLogic;
        this.nullChecker = nullChecker;
        this.orderLogic = orderLogic;
    }

    @Override
    public List<DriverMinInfoDTO> getAll() {
        return driverRepository.getAll().stream()
                .map(DriverMinInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DriverInfoDTO get(int id) {
        Driver d = driverRepository.get(id);
        nullChecker.throwNotFoundIfNull(d, Driver.class, id);

        return new DriverInfoDTO(d);
    }

    @Override
    public List<DriverInfoDTO> getByOrderId(int orderId) {
        String hql = "from Driver d where d.currentOrder= " + orderId;

        return driverRepository.getByQuery(hql, null).stream()
                .map(DriverInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<DriverInfoDTO> getAvailable(int orderId) {

        Order order = orderRepository.get(orderId);
        nullChecker.throwNotFoundIfNull(order, Order.class, orderId);

        Vehicle assignedVehicle = order.getAssignedVehicle();
        nullChecker.throwNotAssignedIfNull(assignedVehicle, Vehicle.class, orderId);

        City city = assignedVehicle.getCurrentCity();
        nullChecker.throwNotFoundIfNull(city, City.class, orderId);

        int hoursRemainPerWorker = WORKING_HOURS_PER_MONTH - orderLogic.getHoursPerWorker(order);
        String hql = "from Driver d where d.status='ON_REST' and d.currentOrder=null and d.currentCity =:city and d.hoursWorked<=:hours";

        HashMap<String, Object> params = new HashMap<>();
        params.put("city", city);
        params.put("hours", hoursRemainPerWorker);

        return driverRepository.getByQuery(hql, params).stream()
                .map(DriverInfoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDeskInfoDTO getDriverDeskInfo(String username) {
        User user = userService.get(username);
        nullChecker.throwNotFoundIfNull(user, User.class,username);

        String hql="from Driver d where d.user=:user";

       List<Driver> result=driverRepository.getByQuery(hql, Map.of("user",user));
        nullChecker.throwNotFoundIfEmptyList(result, String.format(NO_DRIVER_FOR_USER,username));
        Driver driver = result.get(0);
        return new DriverDeskInfoDTO(driver);
    }

    @Override
    public int save(NewDriverDTO dto) {
        String username = dto.getUsername();
        String password = dto.getPassword();
        User user = new User(username, password, true, null);

        userService.saveDriver(user);

        return driverRepository.save(dto.toDriver());
    }

    @Override
    public int delete(int id) {
        Driver driver = driverRepository.get(id);
        nullChecker.throwNotFoundIfNull(driver, Driver.class, id);
        checkProvider.canBeDeleted(driver);

        userService.delete(driver.getUser());
        driverRepository.delete(driver);
        return id;
    }

    @Override
    public int update(DriverUpdateDTO dto) {
        Driver driver = driverRepository.get(dto.getId());
        nullChecker.throwNotFoundIfNull(driver, Driver.class, dto.getId());

        checkProvider.canBeUpdated(driver, dto);

        driverLogic.updateDriver(driver, dto);

        driverRepository.update(driver);
        return dto.getId();
    }

    @Override
    public int update(UpdateStatusDriverDTO dto) {
        DriverStatus status = dto.getStatus();
        int driverId = dto.getId();

        Driver driver = driverRepository.get(driverId);
        nullChecker.throwNotFoundIfNull(driver, Driver.class, driverId);

        Order order = driver.getCurrentOrder();
        nullChecker.throwNotFoundIfNull(order, ORDER_IS_NULL);

        driverLogic.updateStatus(order, driver, status);
        driverRepository.update(driver);
        return driverId;
    }
}
