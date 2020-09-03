package main.core.orderManagement.cargo.services;

import main.core.orderManagement.cargo.DTO.NewCargoDTO;
import main.core.orderManagement.cargo.entity.CargoStatus;
import main.core.driver.entity.Driver;
import main.global.exceptionHandling.exceptions.SaveFailedException;
import main.global.exceptionHandling.exceptions.UpdateFailException;

import java.util.List;

import static main.core.orderManagement.cargo.entity.CargoStatus.DELIVERED;
import static main.core.orderManagement.cargo.entity.CargoStatus.PREPARED;
import static main.core.driver.entity.DriverStatus.ON_DUTY_DRIVING;

public class CargoCheckProvider {
    private static final String STATUS_UPDATE_ERR = "Cargo status can be changed in order PREPARED->TRANSPORTING->DELIVERED only!";
    private static final String STATUS_DID_NOT_CHANGED = "New status is the same as the previous one!";
    private static final String NOBODY_IS_DRIVING = "There is no driver with status ON_DUTY_DRIVING!";
    private static final String SAVE_FAIL_NAME_ERR = "Name must have from 1 to 255 characters long!";
    private static final String SAVE_FAIL_WEIGHT_ERR = "Weight must be from 1 to 1000!";
    private static final String SAVE_FAIL_NULL = "No cargo found to save!";


    public void canBeUpdated(List<Driver> drivers, CargoStatus cargoStatus, CargoStatus dtoStatus) {
        boolean isDowngradingOrJumping = (cargoStatus != PREPARED && dtoStatus == PREPARED)
                || (cargoStatus == DELIVERED && dtoStatus != DELIVERED)
                || (cargoStatus == PREPARED && dtoStatus == DELIVERED);

        boolean noOneIdDriving = drivers.stream()
                .allMatch(d -> d.getStatus() != ON_DUTY_DRIVING);

        if (isDowngradingOrJumping) throw new UpdateFailException(STATUS_UPDATE_ERR);

        if (noOneIdDriving) throw new UpdateFailException(NOBODY_IS_DRIVING);

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
