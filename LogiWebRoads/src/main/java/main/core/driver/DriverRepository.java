package main.core.driver;

import main.model.logistic.City;
import main.model.users.Driver;

import java.util.List;

public interface DriverRepository {
    List<Driver> getAll();

    Driver get(int id);

    List<Driver> getByQuery(String hql);

    int save(Driver driver);

    void update(Driver driver);

    Driver delete(Driver driver);

    List<Driver> getAvailable(int hoursPerWorker, City city);
}
