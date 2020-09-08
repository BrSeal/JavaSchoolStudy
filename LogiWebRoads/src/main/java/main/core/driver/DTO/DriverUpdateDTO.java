package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverUpdateDTO {
    private int id;
    private String firstName ;
    private String lastName;
    private int currentCityId;
    private int hoursWorked;
    private int currentOrder;
}
