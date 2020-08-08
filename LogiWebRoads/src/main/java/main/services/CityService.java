package main.services;

import main.model.logistic.City;

import java.util.List;

public interface CityService {
    List<City> getAll();

    City get(int id);
}
