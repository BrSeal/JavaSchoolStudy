package main.services;

import main.model.users.Driver;
import main.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

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
    public int save(Driver driver) {
        return repository.save(driver);
    }

    @Override
    public Driver delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Driver delete(Driver driver) {
        return repository.delete(driver);
    }
}
