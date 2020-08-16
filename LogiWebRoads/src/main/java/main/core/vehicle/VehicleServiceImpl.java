package main.core.vehicle;

import main.core.order.OrderRepository;
import main.core.vehicle.DTO.VehicleDTO;
import main.model.logistic.Order;
import main.model.logistic.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static main.core.order.services.OrderCalculator.calculateMaxLoad;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository vehicleRepository;
    private final OrderRepository orderRepository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository vehicleRepository, OrderRepository orderRepository) {
        this.vehicleRepository = vehicleRepository;
        this.orderRepository = orderRepository;
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
        Order order= orderRepository.get(orderId);
        int maxLoad=calculateMaxLoad(order);

        String hql = "from Vehicle v where v.currentOrder=null and v.isOk=true and v.capacity>" + maxLoad;
        return vehicleRepository.getQueryResult(hql).stream().map(VehicleDTO::new).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO get(int id) {
        return new VehicleDTO(vehicleRepository.get(id));
    }

    @Override
    public int save(VehicleDTO dto) {
       return vehicleRepository.save(dto.toVehicle());
    }

    @Override
    public int delete(int id) {
        return delete(vehicleRepository.get(id));
    }

    @Override
    public int delete(Vehicle vehicle) {
        if (!canBeDeleted(vehicle)) {
            throw new IllegalArgumentException("Vehicle №" + vehicle.getId()
                    + " is currently in order №" + vehicle.getCurrentOrder() + "!");
        }
         vehicleRepository.delete(vehicle);
          return vehicle.getId();
    }

    @Override
    public int update(VehicleDTO dto) {
        vehicleRepository.update(dto.toVehicle());
        return dto.getId();
    }

    private boolean canBeDeleted(Vehicle v) {
        return v.getCurrentOrder() == null;
    }
}
