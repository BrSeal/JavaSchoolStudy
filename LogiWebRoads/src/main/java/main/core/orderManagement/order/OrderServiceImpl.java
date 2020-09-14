package main.core.orderManagement.order;

import main.core.cityAndRoads.cities.CityRepository;
import main.core.cityAndRoads.cities.entity.City;
import main.core.cityAndRoads.roads.RoadRepository;
import main.core.driver.DriverRepository;
import main.core.driver.entity.Driver;
import main.core.orderManagement.order.DTO.*;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.orderManagement.order.services.OrderLogic;
import main.core.vehicle.VehicleRepository;
import main.core.vehicle.entity.Vehicle;
import main.global.exceptionHandling.NullChecker;
import main.global.messaging.JMSProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jms.JMSException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final RoadRepository roadRepository;
    private final OrderCheckProvider orderCheckProvider;
    private final OrderLogic orderLogic;
    private final CityRepository cityRepository;
    private final NullChecker nullChecker;
    private final JMSProvider jmsProvider;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, VehicleRepository vehicleRepository, DriverRepository driverRepository, RoadRepository roadRepository, OrderCheckProvider orderCheckProvider, OrderLogic orderLogic, CityRepository cityRepository, NullChecker nullChecker, JMSProvider jmsProvider) {
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.roadRepository = roadRepository;
        this.orderCheckProvider = orderCheckProvider;
        this.orderLogic = orderLogic;
        this.cityRepository = cityRepository;
        this.nullChecker = nullChecker;
        this.jmsProvider = jmsProvider;
    }

    @Override
    public int save(NewOrderDTO dto) {
        List<Integer> cityIds = cityRepository.getAll().stream().map(City::getId).collect(Collectors.toList());
        orderCheckProvider.validateNew(dto, cityIds);

        Order order = dto.toOrder();

        orderLogic.calculateRoute(order, roadRepository.getAll());

        try {
            jmsProvider.sendMessage();
        } catch (JMSException e) {
            e.printStackTrace();
        }

        return orderRepository.save(order);
    }

    @Override
    public List<SmallInfoOrderDTO> getAll() {
        return orderRepository.getAll().stream()
                .map(SmallInfoOrderDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public InfoOrderDTO get(int id) {
        Order order =orderRepository.get(id);
        nullChecker.throwNotFoundIfNull(order,Order.class,id);
        return new InfoOrderDTO(order);
    }

    @Override
    public void assignVehicle(AssignVehicleOrderDTO dto) {
        int orderId = dto.getId();
        int assignedVehicleId = dto.getVehicleId();

        Order order = orderRepository.get(orderId);
        nullChecker.throwNotFoundIfNull(order, Order.class, orderId);

        Vehicle vehicle = vehicleRepository.get(assignedVehicleId);
        nullChecker.throwNotFoundIfNull(vehicle, Vehicle.class, assignedVehicleId);

        orderCheckProvider.vehicleAssignmentCheck(order, vehicle);

        if (order.getAssignedVehicle()!=null) order.getAssignedVehicle().setCurrentOrder(null);

        vehicle.setCurrentOrder(order);
        order.setAssignedVehicle(vehicle);

        orderRepository.update(order);
    }

    @Override
    public void assignDrivers(AssignDriversOrderDTO dto) {
        int id = dto.getId();
        Order order = orderRepository.get(id);
        nullChecker.throwNotFoundIfNull(order, Order.class, id);

        List<Driver> drivers = dto.getDriverIds().stream()
                .map(driverId -> {
                            Driver driver=  driverRepository.get(driverId);
                            nullChecker.throwNotFoundIfNull(driver,Driver.class,driverId);
                            return driver;
                        })
                        .collect(Collectors.toList());

        orderCheckProvider.driverAssignmentCheck(order, drivers);

        order.setAssignedDrivers(drivers);
        drivers.forEach(d -> d.setCurrentOrder(order));

        orderRepository.update(order);
    }
}
