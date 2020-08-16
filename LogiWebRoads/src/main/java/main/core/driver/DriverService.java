package main.core.driver;

import main.core.driver.DTO.DriverDTO;
import main.core.driver.DTO.DriverDeskInfoDTO;
import main.core.driver.DTO.DriverInfoDTO;
import main.model.users.Driver;

import java.util.List;

public interface DriverService {
    List<DriverInfoDTO> getAll();

    DriverInfoDTO get(int id);

    List<DriverInfoDTO> getByOrderId(int orderId);

    int save(DriverDTO e);

    int update(DriverInfoDTO e);

    int delete(int id);

    int delete(Driver driver);

    List<DriverInfoDTO> getAvailable(int orderId);

    DriverDeskInfoDTO getDriverDeskInfo(int id);
}