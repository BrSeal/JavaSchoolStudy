package main.services;

import main.model.logistic.Vehicle;
import main.repositories.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {
    
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
    public Vehicle get(int id) {
        return repository.get(id);
    }

    @Override
    public int save(Vehicle vehicle) {
        return repository.save(vehicle);
    }

    @Override
    public Vehicle delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Vehicle delete(Vehicle vehicle) {
        return repository.delete(vehicle);
    }
}
