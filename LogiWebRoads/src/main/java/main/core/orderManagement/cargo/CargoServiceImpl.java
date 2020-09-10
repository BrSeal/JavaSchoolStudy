package main.core.orderManagement.cargo;

import main.core.driver.DriverRepository;
import main.core.driver.entity.Driver;
import main.core.orderManagement.cargo.DTO.InfoCargoDTO;
import main.core.orderManagement.cargo.DTO.SmallCargoDTO;
import main.core.orderManagement.cargo.DTO.UpdateStatusCargoDTO;
import main.core.orderManagement.cargo.entity.Cargo;
import main.core.orderManagement.cargo.services.CargoCheckProvider;
import main.core.orderManagement.cargo.services.CargoLogic;
import main.core.orderManagement.order.OrderRepository;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.waypoint.WaypointRepository;
import main.core.orderManagement.waypoint.entity.Waypoint;
import main.global.exceptionHandling.NullChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static main.core.orderManagement.waypoint.entity.WaypointType.LOAD;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    private static final String CORRUPTED_DATA="Cargo #%d data is corrupted!";

    private final CargoRepository cargoRepository;
    private final CargoCheckProvider cargoCheckProvider;
    private final CargoLogic cargoLogic;
    private final WaypointRepository waypointRepository;
    private final OrderRepository orderRepository;
    private final NullChecker nullChecker;
    private final DriverRepository driverRepository;

    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository, CargoCheckProvider cargoCheckProvider, WaypointRepository waypointRepository, OrderRepository orderRepository, CargoLogic cargoLogic, NullChecker nullChecker, DriverRepository driverRepository) {

        this.cargoRepository = cargoRepository;
        this.cargoCheckProvider = cargoCheckProvider;
        this.waypointRepository = waypointRepository;
        this.orderRepository = orderRepository;
        this.cargoLogic = cargoLogic;
        this.nullChecker = nullChecker;
        this.driverRepository = driverRepository;
    }

    @Override
    public List<SmallCargoDTO> getAll() {
        return cargoRepository.getAll().stream()
                .map(SmallCargoDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public InfoCargoDTO get(int id) {
        Cargo cargo=cargoRepository.get(id);
        nullChecker.throwNotFoundIfNull(cargo,Cargo.class,id);

        int from;
        int to;
        int order;

        List<Waypoint> waypoints = waypointRepository.getByCargo(cargo);

        String errMsg=String.format(CORRUPTED_DATA,id);
        nullChecker.throwNotFoundIfEmptyList(waypoints,errMsg);
        if(waypoints.size()!=2) throw new IllegalArgumentException(errMsg);

        if (waypoints.get(0).getType() == LOAD) {
            from = waypoints.get(0).getCity().getId();
            to = waypoints.get(1).getCity().getId();
        } else {
            from = waypoints.get(1).getCity().getId();
            to = waypoints.get(0).getCity().getId();
        }
        order = waypoints.get(0).getOrder().getId();

        return new InfoCargoDTO(cargo, from, to, order);
    }

    @Override
    public void update(UpdateStatusCargoDTO dto) {
        Cargo cargo = cargoRepository.get(dto.getId());
        List<Waypoint> waypoints = waypointRepository.getByCargo(cargo);
        Order order = waypoints.get(0).getOrder();
        List<Driver> drivers=driverRepository.getByOrderId(order.getId());

        cargoCheckProvider.canBeUpdated(drivers, cargo.getStatus(),dto.getStatus(),order.getAssignedVehicle());
        cargoLogic.updateStatus(cargo, dto, order);
        orderRepository.update(order);
    }


}
