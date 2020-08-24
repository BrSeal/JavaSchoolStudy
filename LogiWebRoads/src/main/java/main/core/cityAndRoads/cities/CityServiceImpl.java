package main.core.cityAndRoads.cities;

import main.core.cityAndRoads.cities.services.CityCheckProvider;
import main.model.logistic.City;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private final CityRepository repository;
    private final CityCheckProvider checkIf;

    @Autowired
    public CityServiceImpl(CityRepository repository, CityCheckProvider checkIf) {
        this.repository = repository;
        this.checkIf = checkIf;
    }

    @Override
    public List<City> getAll() {
        return repository.getAll();
    }

    @Override
    public City get(int id) {
        City city=repository.get(id);
        checkIf.ifNotFound(city);
        return city;
    }

}
