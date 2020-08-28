package main.core.vehicle;

import main.core.cityAndRoads.cities.CityRepository;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.OrderRepository;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.services.OrderLogic;
import main.core.vehicle.DTO.NewVehicleDTO;
import main.core.vehicle.DTO.VehicleDTO;
import main.core.vehicle.entity.Vehicle;
import main.core.vehicle.services.VehicleCheckProvider;
import main.core.vehicle.services.VehicleLogic;
import main.global.exceptionHandling.NullChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final OrderRepository orderRepository;
    private final VehicleCheckProvider vehicleCheckProvider;
    private final CityRepository cityRepository;
    private final OrderLogic orderLogic;
    private final NullChecker nullChecker;
    private final VehicleLogic vehicleLogic;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, OrderRepository orderRepository, VehicleCheckProvider vehicleCheckProvider, CityRepository cityRepository, OrderLogic orderLogic, NullChecker nullChecker, VehicleLogic vehicleLogic) {
        this.vehicleRepository = vehicleRepository;
        this.orderRepository = orderRepository;
        this.vehicleCheckProvider = vehicleCheckProvider;
        this.cityRepository = cityRepository;
        this.orderLogic = orderLogic;
        this.nullChecker = nullChecker;
        this.vehicleLogic = vehicleLogic;
    }

    @Override
    public List<VehicleDTO> getAll() {
        return vehicleRepository.getAll().stream()
                .map(VehicleDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getByOrderId(int orderId) {
        String hql = "from Vehicle v where v.currentOrder=" + orderId;
        return vehicleRepository.getQueryResult(hql).stream()
                .map(VehicleDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getAvailable(int orderId) {
        Order order = orderRepository.get(orderId);
        int maxLoad = orderLogic.calculateMaxLoad(order.getWaypoints());

        String hql = "from Vehicle v where v.currentOrder=null and v.ok=true and v.capacity>" + maxLoad;
        return vehicleRepository.getQueryResult(hql).stream().map(VehicleDTO::new).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO get(int id) {
        Vehicle vehicle = vehicleRepository.get(id);
        nullChecker.throwNotFoundIfNull(vehicle, Vehicle.class, id);
        return new VehicleDTO(vehicle);
    }

    @Override
    public int save(NewVehicleDTO dto) {
        List<Integer> cityIds = cityRepository.getAll()
                .stream()
                .map(City::getId)
                .collect(Collectors.toList());
        List<String> regNums = vehicleRepository.getAll()
                .stream()
                .map(Vehicle::getRegNumber)
                .collect(Collectors.toList());

        vehicleCheckProvider.validateNew(dto, regNums, cityIds);

        return vehicleRepository.save(dto.toVehicle());
    }

    @Override
    public int delete(int id) {
        Vehicle vehicle=vehicleRepository.get(id);
        nullChecker.throwNotFoundIfNull(vehicle,Vehicle.class,id);
        vehicleCheckProvider.canBeDeleted(vehicle);

        vehicleRepository.delete(vehicle);
        return id;
    }

    @Override
    public int update(VehicleDTO dto) {
        Vehicle vehicle=vehicleRepository.get(dto.getId());
        nullChecker.throwNotFoundIfNull(vehicle,Vehicle.class,dto.getId());

        vehicleCheckProvider.canBeUpdated(vehicle,dto);

        vehicleLogic.updateFields(vehicle,dto);

        vehicleRepository.update(vehicle);

        return dto.getId();
    }
}
