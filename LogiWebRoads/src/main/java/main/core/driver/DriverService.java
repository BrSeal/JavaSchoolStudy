package main.core.driver;

import main.core.driver.DTO.*;

import java.util.List;

public interface DriverService {
    List<DriverMinInfoDTO> getAll();

    DriverInfoDTO get(int id);

    List<DriverInfoDTO> getByOrderId(int orderId);

    int save(NewDriverDTO dto);

    int update(DriverUpdateDTO dto);

    int update(UpdateStatusDriverDTO dto, String username);

    int delete(int id);

    List<DriverInfoDTO> getAvailable(int orderId);

    DriverDeskInfoDTO getDriverDeskInfo();
}
