package main.core.cityAndRoads.cities;

import main.core.cityAndRoads.cities.entity.City;

import java.util.List;

public interface CityService {
    List<City> getAll();

    City get(int id);
}
