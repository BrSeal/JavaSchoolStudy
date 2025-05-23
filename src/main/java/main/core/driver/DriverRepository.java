package main.core.driver;

import main.core.driver.entity.Driver;

import java.util.List;
import java.util.Map;

public interface DriverRepository {
    List<Driver> getAll();

    Driver get(int id);

    List<Driver> getByQuery(String hql, Map<String,Object> params);

    List<Driver> getByOrderId(int orderId);

    int save(Driver driver);

    void update( Driver driver);

    Driver delete(Driver driver);
}
