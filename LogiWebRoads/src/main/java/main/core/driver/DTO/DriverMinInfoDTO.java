package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.driver.entity.Driver;
import main.core.driver.entity.DriverStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverMinInfoDTO {
    private int id;
    private String firstName ;
    private String lastName;
    private int currentCityId;
    private DriverStatus status;

    public DriverMinInfoDTO(Driver driver){
        firstName = driver.getFirstName();
        lastName = driver.getLastName();
        id=driver.getId();
        currentCityId=driver.getCurrentCity().getId();
        status=driver.getStatus();
    }
}
