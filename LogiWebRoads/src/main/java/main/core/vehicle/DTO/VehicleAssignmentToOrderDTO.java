package main.core.vehicle.DTO;

import main.core.vehicle.entity.Vehicle;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleAssignmentToOrderDTO {
    private int id;
    private String regNumber;

    public VehicleAssignmentToOrderDTO(Vehicle v) {
        id = v.getId();
        regNumber = v.getRegNumber().toUpperCase();
    }
}
