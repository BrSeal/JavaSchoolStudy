package main.core.driver.DTO;

import main.exceptionHandling.exceptions.DtoConvertForbiddenException;
import main.model.users.Driver;
import main.model.users.DriverStatus;

public class DriverMinimalDTO implements DriverDTO{
    private int id;
    private int currentCityId;
    private int currentOrderId;
    private DriverStatus status;

    public DriverMinimalDTO(Driver driver){
        id=driver.getId();
        currentCityId=driver.getCurrentCity().getId();
        currentOrderId=driver.getCurrentOrder()==null?0:driver.getCurrentOrder().getId();
        status=driver.getStatus();
    }

    @Override
    public Driver toDriver() {
        throw new DtoConvertForbiddenException();
    }
}
