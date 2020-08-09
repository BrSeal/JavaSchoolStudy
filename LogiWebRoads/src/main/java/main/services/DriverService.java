package main.services;

import main.model.users.Driver;
import main.model.users.DriverDTO;

import java.util.List;

public interface DriverService {
    List<Driver> getAll();

    Driver get(int id);

    int save(DriverDTO e);

    void update(DriverDTO e);

    Driver delete(int id);

    Driver delete(Driver driver);
}
