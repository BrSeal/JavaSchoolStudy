package main.core.orderManagement.order;

import main.core.cityAndRoads.cities.CityRepository;
import main.core.cityAndRoads.roads.RoadRepository;
import main.core.driver.DriverRepository;
import main.core.orderManagement.order.DTO.InfoOrderDTO;
import main.core.orderManagement.order.DTO.OrderDTO;
import main.core.orderManagement.order.DTO.SmallInfoOrderDTO;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.orderManagement.order.services.OrderLogic;
import main.core.vehicle.VehicleRepository;
import main.core.orderManagement.waypoint.services.WaypointCheckProvider;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.entity.Order;
import main.core.vehicle.entity.Vehicle;
import main.core.driver.entity.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private final WaypointCheckProvider waypointCheckProvider;
    private final CityRepository cityRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, VehicleRepository vehicleRepository, DriverRepository driverRepository, RoadRepository roadRepository, OrderCheckProvider orderCheckProvider, OrderLogic orderLogic, WaypointCheckProvider waypointCheckProvider, CityRepository cityRepository) {
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.roadRepository = roadRepository;
        this.orderCheckProvider = orderCheckProvider;
        this.orderLogic = orderLogic;
        this.waypointCheckProvider = waypointCheckProvider;
        this.cityRepository = cityRepository;
    }

    @Override
    public int save(OrderDTO dto) {
        Order order = dto.toOrder();
        List<City> cities=cityRepository.getAll();

        waypointCheckProvider.cityCheck(order.getWaypoints(),cities);
        orderLogic.calculateRoute(order, roadRepository.getAll());
        return orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getAll() {
        return orderRepository.getAll().stream()
                .map(SmallInfoOrderDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO get(int id) {
        return new InfoOrderDTO(orderRepository.get(id));
    }

    @Override
    public void assignVehicle(OrderDTO dto) {
        Order fromDTO = dto.toOrder();


        Order order = orderRepository.get(fromDTO.getId());

        orderCheckProvider.isOrderCompleted(order);

        Vehicle vehicle = vehicleRepository.get(fromDTO.getAssignedVehicle().getId());

        orderCheckProvider.vehicleAssignmentCheck(order, vehicle, orderLogic.calculateMaxLoad(order.getWaypoints()));

        if (orderCheckProvider.isVehicleAssigned(order)) order.getAssignedVehicle().setCurrentOrder(null);

        vehicle.setCurrentOrder(order);

        orderRepository.update(order);
    }

    @Override
    public void assignDrivers(OrderDTO dto) {
        Order fromDTO = dto.toOrder();
        Order order = orderRepository.get(fromDTO.getId());

        orderCheckProvider.isDriverListEmptyOrNull(fromDTO.getAssignedDrivers());
        orderCheckProvider.isOrderCompleted(order);

        List<Driver> drivers = fromDTO.getAssignedDrivers().stream()
                .map(d -> driverRepository.get(d.getId()))
                .collect(Collectors.toList());

        int hoursPerWorker=orderLogic.calculateOrderWorkTimeFirstMonth(order);

        orderCheckProvider.driverAssignmentCheck(order, drivers,hoursPerWorker);

        drivers.forEach(d -> d.setCurrentOrder(order));

        orderRepository.update(order);
    }

}
