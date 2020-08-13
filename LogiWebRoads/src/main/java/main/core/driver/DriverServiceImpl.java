package main.core.driver;

import main.model.users.Driver;
import main.model.users.DriverStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<DriverDTO> getAll() {
        return repository.getAll().stream()
                .map(DriverDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public DriverDTO get(int id) {
        return new DriverDTO(repository.get(id));
    }

    @Override
    public List<DriverDTO> getByOrderId(int orderId) {
        return repository.getByOrderId(orderId).stream()
                .map(DriverDTO::new)
                .collect(Collectors.toList());
    }

    @Override
    public String save(DriverDTO dto) {
        int id = repository.save(dto.toDriver());
        return String.format(SAVE_SUCCESS, id);
    }

    @Override
    public String delete(int id) {
        return delete(repository.get(id));
    }

    @Override
    public String delete(Driver driver) {
        if (canBeDeleted(driver)) {
            repository.delete(driver);
            return String.format(DELETE_SUCCESS, driver.getId());
        }
        return String.format(DELETE_FAIL, driver.getId());
    }

    @Override
    public String update(DriverDTO dto) {
        repository.update(dto.toDriver());
        return String.format(UPDATE_SUCCESS, dto.getId());
    }

    private boolean canBeDeleted(Driver d) {
        return d.getCurrentOrder() == null && d.getStatus().equals(DriverStatus.ON_REST);
    }
}
