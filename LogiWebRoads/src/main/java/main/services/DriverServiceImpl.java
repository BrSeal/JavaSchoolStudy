package main.services;

import main.model.logistic.City;
import main.model.users.Driver;
import main.model.users.DriverDTO;
import main.model.users.DriverStatus;
import main.repositories.CityRepository;
import main.repositories.DriverRepository;
import main.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private static final String DELETE_FAIL = "Deletion failed! Driver is currently on order!";
    private static final String DELETE_SUCCESS = "Driver was successfully deleted";

    private final DriverRepository repository;

    @Autowired
    public DriverServiceImpl(DriverRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Driver> getAll() {
        return repository.getAll();
    }

    @Override
    public Driver get(int id) {
        return repository.get(id);
    }

    @Override
    public int save(DriverDTO dto) {
        return repository.save(dto.toDriver());
    }

    @Override
    public String delete(int id) {
        Driver driver=repository.get(id);
       if( !canBeDeleted(driver)) return DELETE_FAIL;
        repository.delete(driver);
        return DELETE_SUCCESS;
    }

    @Override
    public String delete(Driver driver) {
        if( !canBeDeleted(driver)) return DELETE_FAIL;
        repository.delete(driver);
        return DELETE_SUCCESS;
    }

    @Override
    public void update(DriverDTO dto) {
        repository.update(dto.toDriver());
    }

    private boolean canBeDeleted(Driver d){
        return d.getStatus().equals(DriverStatus.ON_REST);
    }
}
