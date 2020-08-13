package main.core.driver;

import main.model.users.Driver;

import java.util.List;

public interface DriverService {
    List<DriverDTO> getAll();

    DriverDTO get(int id);

    List<DriverDTO> getByOrderId(int orderId);

    String save(DriverDTO e);

    String update(DriverDTO e);

    String delete(int id);

    String delete(Driver driver);
}
