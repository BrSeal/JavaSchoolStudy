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
public class UpdateStatusDriverDTO{
private int id;
private DriverStatus status;
}
