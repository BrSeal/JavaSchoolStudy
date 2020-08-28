package main.core.cargo.services;

import main.core.cargo.entity.Cargo;
import main.core.cargo.entity.CargoStatus;
import main.core.driver.entity.Driver;
import main.global.exceptionHandling.exceptions.NotFoundException;

import java.util.List;

import static main.core.cargo.entity.CargoStatus.DELIVERED;
import static main.core.cargo.entity.CargoStatus.PREPARED;
import static main.core.driver.entity.DriverStatus.ON_DUTY_DRIVING;

public class CargoCheckProvider {
    private static final String STATUS_UPDATE_ERR = "Cargo status can be changed in order PREPARED->TRANSPORTING->DELIVERED only!";
    private static final String STATUS_DID_NOT_CHANGED = "New status is the same as the previous one!";
    private static final String NOBODY_IS_DRIVING = "There is no driver with status ON_DUTY_DRIVING!";


    public void canBeUpdated(List<Driver> drivers, CargoStatus cargoStatus, CargoStatus dtoStatus) {
        boolean isDowngradingOrJumping =
                (cargoStatus != PREPARED && dtoStatus == PREPARED)
                        ||
                        (cargoStatus == DELIVERED && dtoStatus != DELIVERED)
                        ||
                        (cargoStatus == PREPARED && dtoStatus == DELIVERED);

        boolean noOneIdDriving = drivers.stream()
                .allMatch(d->d.getStatus()!= ON_DUTY_DRIVING);

        if (isDowngradingOrJumping) throw new IllegalArgumentException(STATUS_UPDATE_ERR);

        if (noOneIdDriving) throw new IllegalArgumentException(NOBODY_IS_DRIVING);

        if (cargoStatus == dtoStatus) throw new IllegalArgumentException(STATUS_DID_NOT_CHANGED);
    }

    public void checkNPE(Cargo cargo,int id){
        if(cargo==null) throw new NotFoundException(Cargo.class,id);
    }
}
