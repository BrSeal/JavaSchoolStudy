package main.repositories;

import main.model.users.Driver;
import java.util.List;

public interface DriverRepository {
    List<Driver> getAll();

    Driver get(int id);

    int save(Driver driver);

    void update(Driver driver);

    Driver delete(Driver driver);
}
