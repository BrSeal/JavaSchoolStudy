package main.core.orderManagement.cargo.services;

import main.core.driver.entity.Driver;
import main.core.driver.services.DriverCheckProvider;
import main.core.orderManagement.cargo.DTO.NewCargoDTO;
import main.core.orderManagement.cargo.entity.CargoStatus;
import main.core.vehicle.entity.Vehicle;
import main.core.vehicle.services.VehicleCheckProvider;
import main.global.exceptionHandling.exceptions.SaveFailedException;
import main.global.exceptionHandling.exceptions.UpdateFailException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.List;

import static main.core.orderManagement.cargo.entity.CargoStatus.DELIVERED;
import static main.core.orderManagement.cargo.entity.CargoStatus.PREPARED;

public class CargoCheckProvider {
    private static final String STATUS_UPDATE_ERR = "Cargo status can be changed in order PREPARED->TRANSPORTING->DELIVERED only!";
    private static final String STATUS_DID_NOT_CHANGED = "New status is the same as the previous one!";
    private static final String SAVE_FAIL_NAME_ERR = "Name must have from 1 to 255 characters long!";
    private static final String SAVE_FAIL_WEIGHT_ERR = "Weight must be from 1 to 1000!";
    private static final String SAVE_FAIL_NULL = "No cargo found to save!";

    private final DriverCheckProvider driverCheckProvider;
    private final VehicleCheckProvider vehicleCheckProvider;

    @Autowired
    public CargoCheckProvider(DriverCheckProvider driverCheckProvider, VehicleCheckProvider vehicleCheckProvider) {
        this.driverCheckProvider = driverCheckProvider;
        this.vehicleCheckProvider = vehicleCheckProvider;
    }

    public void canBeUpdated(List<Driver> drivers, CargoStatus cargoStatus, CargoStatus dtoStatus, Vehicle vehicle) {
        boolean isDowngradingOrJumping = (cargoStatus != PREPARED && dtoStatus == PREPARED)
                || (cargoStatus == DELIVERED && dtoStatus != DELIVERED)
                || (cargoStatus == PREPARED && dtoStatus == DELIVERED);

        driverCheckProvider.canUpdateCargoStatus(drivers);
        vehicleCheckProvider.isOk(vehicle);

        if (isDowngradingOrJumping) throw new UpdateFailException(STATUS_UPDATE_ERR);

        if (cargoStatus == dtoStatus) throw new UpdateFailException(STATUS_DID_NOT_CHANGED);
    }

    public void validateNew(NewCargoDTO cargo) {
        if(cargo==null) throw new SaveFailedException(SAVE_FAIL_NULL);
        validateName(cargo.getName());
        validateWeight(cargo.getWeight());
    }

    private void validateName(String name) {
        boolean isBadName = (name == null || name.isEmpty() || name.length() > 255);
        if (isBadName) throw new SaveFailedException(SAVE_FAIL_NAME_ERR);
    }

    private void validateWeight(int weight) {
        if (weight <= 0 || weight > 1000) throw new SaveFailedException(SAVE_FAIL_WEIGHT_ERR);
    }
}
