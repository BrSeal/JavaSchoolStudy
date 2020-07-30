package main.services;

import main.model.users.Driver;

import java.util.List;

public interface DriverService {
    List<Driver> getAll();

    Driver get(int id);

    int save(Driver e);

    Driver delete(int id);

    Driver delete(Driver driver);
}
