package main.services;

import main.model.users.Driver;
import main.model.users.DriverDTO;
import main.model.users.DriverStatus;
import main.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private static final String DELETE_FAIL = "Deletion failed! Driver #%d is currently on order!";
    private static final String DELETE_SUCCESS = "Driver #%d was successfully deleted";
    private static final String UPDATE_SUCCESS = "Driver #%d was successfully updated";
    private static final String SAVE_SUCCESS = "Driver #%d was successfully saved";

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
    public String save(DriverDTO dto) {
       int id= repository.save(dto.toDriver());
        return String.format(SAVE_SUCCESS,id);
    }

    @Override
    public String delete(int id) {
      return delete(repository.get(id));
    }

    @Override
    public String delete(Driver driver) {
        if( canBeDeleted(driver)) {
            repository.delete(driver);
            return String.format(DELETE_SUCCESS,driver.getId());
        }
       return String.format(DELETE_FAIL,driver.getId());
    }

    @Override
    public String update(DriverDTO dto) {
        repository.update(dto.toDriver());
        return String.format(UPDATE_SUCCESS,dto.getId());
    }

    private boolean canBeDeleted(Driver d){
        return d.getCurrentOrder()==null&&d.getStatus().equals(DriverStatus.ON_REST);
    }
}
