package main.core.cityAndRoads.cities.services;

import main.model.logistic.City;

public class CityCheckProvider {
    private static final String NOT_FOUND = "City not found!";

    public void ifNotFound(City city){
        if(city==null)
        {
            throw new IllegalArgumentException(NOT_FOUND);
        }

    }
}
