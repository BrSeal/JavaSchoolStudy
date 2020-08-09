package main.model.users;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DriverDTO {
    private int id;
    private String firstName;
    private String lastName;
    private int currentCity;
    private int hoursWorked;
    private DriverStatus status;
}
