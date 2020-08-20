package main.core.driver.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.users.Driver;
import main.model.users.DriverStatus;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateStatusDriverDTO implements DriverDTO{
private int id;
private DriverStatus status;
    @Override
    public Driver toDriver() {
        Driver d=new Driver(null,null,0,status,null,null);
        d.setId(id);
        return d;
    }
}
