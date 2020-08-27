package main.core.driver.DTO;

import main.exceptionHandling.exceptions.DtoConvertForbiddenException;
import main.model.users.Driver;
import main.model.users.DriverStatus;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverMinimalDTO implements DriverDTO{
    private int id;
    private String firstName ;
    private String lastName;
    private int currentCityId;
    private DriverStatus status;

    public DriverMinimalDTO(Driver driver){
        firstName = driver.getFirstName();
        lastName = driver.getLastName();
        id=driver.getId();
        currentCityId=driver.getCurrentCity().getId();
        status=driver.getStatus();
    }

    @Override
    public Driver toDriver() {
        throw new DtoConvertForbiddenException();
    }
}
