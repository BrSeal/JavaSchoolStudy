package main.repositories;

import main.model.users.Driver;

import java.util.List;

public interface DriverRepository {
    List<Driver> getAll();

    Driver get(int id);

    int save(Driver driver);

    Driver delete(int id);

    Driver delete(Driver driver);
}
