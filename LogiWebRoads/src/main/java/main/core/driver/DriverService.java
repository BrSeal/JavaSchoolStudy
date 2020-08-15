package main.core.driver;

import main.core.driver.DTO.DriverDTO;
import main.model.users.Driver;

import java.util.List;

public interface DriverService {
    List<DriverDTO> getAll();

    DriverDTO get(int id);

    List<DriverDTO> getByOrderId(int orderId);

    int save(DriverDTO e);

    int update(DriverDTO e);

    int delete(int id);

    int delete(Driver driver);

    List<DriverDTO> getAvailable(int orderId);
}
