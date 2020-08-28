package main.core.cargo;


import main.core.cargo.DTO.CargoDTO;
import main.core.cargo.DTO.InfoCargoDTO;
import main.core.cargo.DTO.NewCargoDTO;
import main.core.cargo.services.CargoCheckProvider;
import main.core.cargo.services.CargoLogic;
import main.core.orderManagement.order.OrderRepository;
import main.core.orderManagement.waypoint.WaypointRepository;
import main.core.cargo.entity.Cargo;
import main.core.orderManagement.order.entity.Order;
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

    private final CargoRepository cargoRepository;
    private final CargoCheckProvider checkProvider;
    private final CargoLogic cargoLogic;
    private final WaypointRepository waypointRepository;
    private final OrderRepository orderRepository;
    private final NullChecker nullChecker;

    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository, CargoCheckProvider checkProvider, WaypointRepository waypointRepository, OrderRepository orderRepository, CargoLogic cargoLogic, NullChecker nullChecker) {

        this.cargoRepository = cargoRepository;
        this.checkProvider = checkProvider;
        this.waypointRepository = waypointRepository;
        this.orderRepository = orderRepository;
        this.cargoLogic = cargoLogic;
        this.nullChecker = nullChecker;
    }

    @Override
    public List<CargoDTO> getAll() {
        return cargoRepository.getAll().stream()
                .map(cargo -> {
                    int from;
                    int to;
                    int order;
                    List<Waypoint> waypoints = waypointRepository.getByCargo(cargo);
                    if (waypoints.get(0).getType() == LOAD) {
                        from = waypoints.get(0).getCity().getId();
                        to = waypoints.get(1).getCity().getId();
                    } else {
                        from = waypoints.get(1).getCity().getId();
                        to = waypoints.get(0).getCity().getId();
                    }
                    order = waypoints.get(0).getOrder().getId();

                    return new InfoCargoDTO(cargo, from, to, order);
                })
                .collect(Collectors.toList());
    }

    @Override
    public Cargo get(int id) {
        Cargo cargo=cargoRepository.get(id);
        nullChecker.throwNotFoundIfNull(cargo,Cargo.class,id);
        return cargo;
    }

    @Override
    public int save(NewCargoDTO cargo) {
        checkProvider.validateNew(cargo);
        return cargoRepository.save(cargo.toCargo());
    }

    @Override
    public void update(CargoDTO dto) {
        Cargo fromDTO = dto.toCargo();
        Cargo cargo = cargoRepository.get(fromDTO.getId());
        List<Waypoint> waypoints = waypointRepository.getByCargo(cargo);
        Order order = waypoints.get(0).getOrder();
        cargoLogic.updateStatusLogic(cargo, fromDTO, order);

        orderRepository.update(order);
    }


}
