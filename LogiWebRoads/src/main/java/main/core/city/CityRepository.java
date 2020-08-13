package main.core.city;

import main.model.logistic.City;

import java.util.List;

public interface CityRepository {
    List<City> getAll();

    City get(int id);
}
