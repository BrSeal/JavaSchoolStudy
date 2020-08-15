package main.core.cargo;


import main.core.cargo.DTO.CargoDTO;
import main.core.cargo.DTO.InfoCargoDTO;
import main.core.waypoint.WaypointRepository;
import main.model.logistic.Cargo;
import main.model.logistic.Waypoint;
import main.model.logistic.WaypointType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class CargoServiceImpl implements CargoService {

    private static final String UPDATE_SUCCESS = "Cargo #%d was successfully updated";

    private final CargoRepository cargoRepository;
    private final WaypointRepository waypointRepository;


    @Autowired
    public CargoServiceImpl(CargoRepository cargoRepository, WaypointRepository waypointRepository) {
        this.cargoRepository = cargoRepository;
        this.waypointRepository = waypointRepository;
    }

    @Override
    public List<CargoDTO> getAll() {
       return cargoRepository.getAll().stream()
                .map(cargo -> {
                    int from;
                    int to;
                    int order;
                    List<Waypoint> waypoints = waypointRepository.getByCargo(cargo);
                    if (waypoints.get(0).getType() == WaypointType.LOAD) {
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
    public int save(Cargo cargo) {
        return cargoRepository.save(cargo);
    }

    @Override
    public String update(Cargo cargo) {
        cargoRepository.update(cargo);
        return String.format(UPDATE_SUCCESS, cargo.getId());
    }
}
