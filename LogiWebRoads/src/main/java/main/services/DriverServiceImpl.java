package main.services;

import main.model.logistic.City;
import main.model.users.Driver;
import main.model.users.DriverDTO;
import main.repositories.CityRepository;
import main.repositories.DriverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DriverServiceImpl implements DriverService {

    private final DriverRepository repository;
    private final CityRepository cityRepository;

    @Autowired
    public DriverServiceImpl(DriverRepository repository, CityRepository cityRepository) {
        this.repository = repository;
        this.cityRepository = cityRepository;
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

        return repository.save(toDriver(dto));
    }

    @Override
    public Driver delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Driver delete(Driver driver) {
        return repository.delete(driver);
    }

    @Override
    public void update(DriverDTO dto) {
        repository.update(toDriver(dto));
    }

    private Driver toDriver(DriverDTO dto){
        City city=cityRepository.get(dto.getCurrentCity());
        Driver driver=new Driver();
        driver.setId(dto.getId());
        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setCurrentCity(city);
        driver.setStatus(dto.getStatus());
        driver.setHoursWorked(dto.getHoursWorked());

        return driver;
    }
}
