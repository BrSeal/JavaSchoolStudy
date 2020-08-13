package main.core.vehicle;

import main.model.logistic.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    private static final String DELETE_FAIL = "Deletion failed! Vehicle #%d is currently on order!";
    private static final String DELETE_SUCCESS = "Vehicle #%d was successfully deleted";
    private static final String UPDATE_SUCCESS = "Vehicle #%d was successfully updated";
    private static final String SAVE_SUCCESS = "Vehicle #%d was successfully saved";

    private final VehicleRepository repository;

    @Autowired
    public VehicleServiceImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VehicleDTO> getAll() {
        return repository.getAll().stream()
                .map(VehicleDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public List<VehicleDTO> getByOrderId(int orderId){
       return repository.getByOrderId(orderId).stream()
               .map(VehicleDTO::new)
               .collect(Collectors.toList());
    }

    @Override
    //TODO Добавить логику
    public List<VehicleDTO> getAvailable(int orderId) {
        return repository.getAll().stream().map(VehicleDTO::new).collect(Collectors.toList());
    }

    @Override
    public VehicleDTO get(int id) {
        return new VehicleDTO(repository.get(id));
    }

    @Override
    public String save(VehicleDTO dto) {
        int id= repository.save(dto.toVehicle());
        return String.format(SAVE_SUCCESS,id);
    }

    @Override
    public String delete(int id) {
        return delete(repository.get(id));
    }

    @Override
    public String delete(Vehicle vehicle) {
        if( canBeDeleted(vehicle)) {
            repository.delete(vehicle);
            return String.format(DELETE_SUCCESS,vehicle.getId());
        }
        return String.format(DELETE_FAIL,vehicle.getId());
    }

    @Override
    public String update(VehicleDTO dto) {
        repository.update(dto.toVehicle());
        return String.format(UPDATE_SUCCESS,dto.getId());
    }

    private boolean canBeDeleted(Vehicle v){
        return v.getCurrentOrder()==null;
    }


}
