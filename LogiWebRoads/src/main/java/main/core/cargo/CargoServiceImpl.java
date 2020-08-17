package main.core.cargo;


import main.core.cargo.DTO.CargoDTO;
import main.core.cargo.DTO.InfoCargoDTO;
import main.core.order.OrderRepository;
import main.core.waypoint.WaypointRepository;
import main.model.logistic.Cargo;
import main.model.logistic.Order;
import main.model.logistic.Waypoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static main.core.cargo.services.CargoLogic.updateStatusLogic;
import static main.model.logistic.WaypointType.LOAD;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    private final CargoRepository cargoRepository;
    private final WaypointRepository waypointRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository, WaypointRepository waypointRepository, OrderRepository orderRepository) {

        this.cargoRepository = cargoRepository;
        this.waypointRepository = waypointRepository;
        this.orderRepository = orderRepository;
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
        return cargoRepository.get(id);
    }

    @Override
    public int save(CargoDTO cargo) {
        return cargoRepository.save(cargo.toCargo());
    }

    @Override
    public void update(CargoDTO dto) {
        Cargo fromDTO = dto.toCargo();
        Cargo cargo = cargoRepository.get(fromDTO.getId());
        List<Waypoint> waypoints = waypointRepository.getByCargo(cargo);
        Order order= waypoints.get(0).getOrder();
        updateStatusLogic(cargo, fromDTO,  order);

        orderRepository.update(order);
    }


}
