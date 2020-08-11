package main.services;

import main.model.logistic.Vehicle;
import main.model.logistic.VehicleDTO;
import main.model.users.Driver;
import main.model.users.DriverDTO;
import main.model.users.DriverStatus;
import main.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
    public List<Vehicle> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Vehicle> getAvailable(){
       return repository.getAvailable();
    }

    @Override
    public Vehicle get(int id) {
        return repository.get(id);
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
