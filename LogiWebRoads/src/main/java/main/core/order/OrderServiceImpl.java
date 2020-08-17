package main.core.order;

import main.core.cityAndRoads.roads.RoadRepository;
import main.core.driver.DriverRepository;
import main.core.order.DTO.InfoOrderDTO;
import main.core.order.DTO.OrderDTO;
import main.core.vehicle.VehicleRepository;
import main.model.logistic.Order;
import main.model.logistic.Vehicle;
import main.model.users.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static main.core.order.services.OrderLogic.calculateMaxLoad;
import static main.core.order.services.OrderLogic.calculateRoute;
import static main.core.order.services.OrderCheckProvider.*;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;
    private final RoadRepository roadRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, VehicleRepository vehicleRepository, DriverRepository driverRepository, RoadRepository roadRepository)
    {
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
        this.roadRepository = roadRepository;
    }

    @Override
    public int save(OrderDTO dto) {
        Order order = dto.toOrder();
        calculateRoute(order,roadRepository.getAll());
        return orderRepository.save(order);
    }

    @Override
    public List<OrderDTO> getAll() {
        return orderRepository.getAll().stream().map(InfoOrderDTO::new).collect(Collectors.toList());
    }

    @Override
    public OrderDTO get(int id) {
        return new InfoOrderDTO(orderRepository.get(id));
    }

    @Override
    public void assignVehicle(OrderDTO dto) {
        Order fromDTO = dto.toOrder();


        Order order = orderRepository.get(fromDTO.getId());

        isOrderCompleted(order);

        Vehicle vehicle = vehicleRepository.get(fromDTO.getAssignedVehicle().getId());

        vehicleAssignmentCheck(order, vehicle,calculateMaxLoad(order.getWaypoints()));

        if(isVehicleAssigned(order)) order.getAssignedVehicle().setCurrentOrder(null);

        vehicle.setCurrentOrder(order);

        orderRepository.update(order);
    }

    @Override
    public void assignDrivers(OrderDTO dto) {
        Order fromDTO = dto.toOrder();
        Order order= orderRepository.get(fromDTO.getId());

        isOrderCompleted(order);

        List<Driver> drivers=fromDTO.getAssignedDrivers().stream()
                .map(d->driverRepository.get(d.getId()))
                .collect(Collectors.toList());


        driverAssignmentCheck(order, drivers);

        drivers.forEach(d->d.setCurrentOrder(order));

        orderRepository.update(order);
    }

}
