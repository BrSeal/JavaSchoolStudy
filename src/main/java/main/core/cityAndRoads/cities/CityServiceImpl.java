package main.core.cityAndRoads.cities;

import main.core.cityAndRoads.cities.entity.City;
import main.global.exceptionHandling.NullChecker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CityServiceImpl implements CityService {
    private final CityRepository repository;
    private final NullChecker nullChecker;

    @Autowired
    public CityServiceImpl(CityRepository repository, NullChecker nullChecker) {
        this.repository = repository;
        this.nullChecker = nullChecker;
    }

    @Override
    public List<City> getAll() {
        return repository.getAll();
    }

    @Override
    public City get(int id) {
        City city = repository.get(id);
        nullChecker.throwNotFoundIfNull(city, City.class, id);
        return city;
    }

}
