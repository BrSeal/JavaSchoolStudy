package main.core.cityAndRoads.cities;

import main.core.cityAndRoads.cities.entity.City;

import java.util.List;

public interface CityRepository {
    List<City> getAll();

    City get(int id);
}
