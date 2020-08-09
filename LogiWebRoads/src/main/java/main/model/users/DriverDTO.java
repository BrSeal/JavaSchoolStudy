package main.model.users;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverDTO {

    private int id;
    private String firstName;
    private String lastName;
    private int currentCity;
    private int hoursWorked;
    private DriverStatus status;
}
