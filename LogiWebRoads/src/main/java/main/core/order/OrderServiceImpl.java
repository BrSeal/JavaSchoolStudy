package main.core.order;

import main.core.driver.DriverRepository;
import main.core.order.DTO.InfoOrderDTO;
import main.core.order.DTO.OrderDTO;
import main.core.vehicle.VehicleRepository;
import main.model.logistic.City;
import main.model.logistic.Order;
import main.model.logistic.Vehicle;
import main.model.logistic.Waypoint;
import main.model.users.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static main.model.logistic.WaypointType.LOAD;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    private static final String LOW_CAPACITY_ERR = "Capacity of the vehicle №%d is too low for order #%d!";
    private static final String ALREADY_ASSIGNED_ERR = "Vehicle №%d is already assigned to order #%d!";
    private static final String DRIVER_IN_OTHER_CITY_ERR = "Driver №%d is in %s but vehicle is in %s!";
    private static final String DRIVER_IN_ORDER_ERR = "Driver №%d is already assigned to order #%d!";

    private final OrderRepository orderRepository;
    private final VehicleRepository vehicleRepository;
    private final DriverRepository driverRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, VehicleRepository vehicleRepository, DriverRepository driverRepository) {
        this.orderRepository = orderRepository;
        this.vehicleRepository = vehicleRepository;
        this.driverRepository = driverRepository;
    }

    @Override
    public int save(OrderDTO dto) {
        Order order = dto.toOrder();
        calculateRoute(order.getWaypoints());
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
    public Order delete(int id) {
        return orderRepository.delete(orderRepository.get(id));
    }

    @Override
    public Order delete(Order order) {
        return null;
    }

    @Override
    public void assignVehicle(OrderDTO dto) {
        Order fromDTO = dto.toOrder();

        //TODO Вынести проверки в отдельный класс!!!!
        int orderId = fromDTO.getId();
        int maxLoad = calculateMaxLoad(fromDTO.getId());

        Vehicle vehicle = vehicleRepository.get(fromDTO.getAssignedVehicle().getId());
        int capacity = vehicle.getCapacity();
        int vehicleId = vehicle.getId();

        if (vehicle.getCurrentOrder() != null) {
            String errMsg = String.format(ALREADY_ASSIGNED_ERR, vehicleId, vehicle.getCurrentOrder().getId());
            throw new IllegalArgumentException(errMsg);
        }

        if (capacity < maxLoad) throw
                new IllegalArgumentException(String.format(LOW_CAPACITY_ERR, vehicleId, orderId));

        orderRepository.update(fromDTO);
    }

    @Override
    public void assignDrivers(OrderDTO dto) {
        Order fromDTO = dto.toOrder();
        //TODO Вынести проверки в отдельный класс!!!!
        City city = orderRepository
                .get(fromDTO.getId())
                .getAssignedVehicle()
                .getCurrentCity();

        for (Driver d : fromDTO.getAssignedDrivers()) {
            d = driverRepository.get(d.getId());
            if(d.getCurrentOrder()!=null){
                String errMsg = String.format(DRIVER_IN_ORDER_ERR,d.getId(),d.getCurrentOrder().getId());
                throw new IllegalArgumentException(errMsg);
            }
            if (d.getCurrentCity() != city) {
                String errMsg = String.format(DRIVER_IN_OTHER_CITY_ERR, d.getId(), d.getCurrentCity().getName(), city.getName());
                throw new IllegalArgumentException(errMsg);
            }
        }


        orderRepository.update(dto.toOrder());
    }

    @Override
    public int calculateMaxLoad(int orderId) {
        Order order = orderRepository.get(orderId);
        List<Waypoint> waypoints = order.getWaypoints();

        waypoints.sort(Comparator.comparingInt(Waypoint::getPathIndex));

        int maxLoad = 0;
        int currentLoad = 0;
        for (Waypoint w : waypoints) {
            int weight = w.getCargo().getWeight();
            if (w.getType() == LOAD) {
                currentLoad += weight;
                maxLoad = Math.max(currentLoad, maxLoad);
            } else {
                currentLoad -= weight;
            }
        }
        return maxLoad;
    }

    //TODO Написать логику подсчета пути!!!!!!
    private void calculateRoute(List<Waypoint> waypoints) {
        int count = 1;
        for (Waypoint w : waypoints) {
            w.setPathIndex(count++);
        }
    }


}
