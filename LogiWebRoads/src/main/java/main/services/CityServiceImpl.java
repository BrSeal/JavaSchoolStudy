package main.services;

import main.model.logistic.City;
import main.repositories.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private final CityRepository repository;

    @Autowired
    public CityServiceImpl(CityRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<City> getAll() {
        return repository.getAll();
    }

    @Override
    public City get(int id) {
        return repository.get(id);
    }

}
