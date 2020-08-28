package main.core.driver;

import main.core.driver.entity.Driver;

import java.util.List;

public interface DriverRepository {
    List<Driver> getAll();

    Driver get(int id);

    List<Driver> getByQuery(String hql, Object... params);

    int save(Driver driver);

    void update( Driver driver);

    Driver delete(Driver driver);
}
