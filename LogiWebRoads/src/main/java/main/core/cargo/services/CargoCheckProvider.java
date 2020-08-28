package main.core.cargo.services;

import main.core.cargo.DTO.NewCargoDTO;
import main.core.cargo.entity.Cargo;
import main.core.cargo.entity.CargoStatus;
import main.core.driver.DTO.NewDriverDTO;
import main.core.driver.entity.Driver;
import main.global.exceptionHandling.exceptions.NotFoundException;
import main.global.exceptionHandling.exceptions.SaveFailedException;
import main.global.exceptionHandling.exceptions.UpdateFailException;

import java.util.List;

import static main.core.cargo.entity.CargoStatus.DELIVERED;
import static main.core.cargo.entity.CargoStatus.PREPARED;
import static main.core.driver.entity.DriverStatus.ON_DUTY_DRIVING;

public class CargoCheckProvider {
    private static final String STATUS_UPDATE_ERR = "Cargo status can be changed in order PREPARED->TRANSPORTING->DELIVERED only!";
    private static final String STATUS_DID_NOT_CHANGED = "New status is the same as the previous one!";
    private static final String NOBODY_IS_DRIVING = "There is no driver with status ON_DUTY_DRIVING!";
    private static final String SAVE_FAIL_NAME_ERR = "Name must have from 1 to 255 characters long!";
    private static final String SAVE_FAIL_WEIGHT_ERR = "Weight must be from 1 to 1000!";


    public void canBeUpdated(List<Driver> drivers, CargoStatus cargoStatus, CargoStatus dtoStatus) {
        boolean isDowngradingOrJumping = (cargoStatus != PREPARED && dtoStatus == PREPARED)
                        || (cargoStatus == DELIVERED && dtoStatus != DELIVERED)
                        || (cargoStatus == PREPARED && dtoStatus == DELIVERED);

        boolean noOneIdDriving = drivers.stream()
                .allMatch(d->d.getStatus()!= ON_DUTY_DRIVING);

        if (isDowngradingOrJumping) throw new UpdateFailException(STATUS_UPDATE_ERR);

        if (noOneIdDriving) throw new UpdateFailException(NOBODY_IS_DRIVING);

        if (cargoStatus == dtoStatus) throw new UpdateFailException(STATUS_DID_NOT_CHANGED);
    }

    public void validateNew(NewCargoDTO cargo){
        if(cargo.getName()==null||cargo.getName().isEmpty()) throw new SaveFailedException(SAVE_FAIL_NAME_ERR);
        if(cargo.getWeight()<0||cargo.getWeight()>1000) throw new SaveFailedException(SAVE_FAIL_WEIGHT_ERR);
    }
}
