package main.services;

import main.model.users.Driver;
import main.model.users.DriverDTO;

import java.util.List;

public interface DriverService {
    List<Driver> getAll();

    Driver get(int id);

    String save(DriverDTO e);

    String update(DriverDTO e);

    String delete(int id);

    String delete(Driver driver);
}
