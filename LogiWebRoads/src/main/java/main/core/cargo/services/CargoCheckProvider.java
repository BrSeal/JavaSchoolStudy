package main.core.cargo.services;

import main.model.logistic.Cargo;
import main.model.logistic.CargoStatus;
import main.model.users.Driver;

import java.util.List;

import static main.model.logistic.CargoStatus.DELIVERED;
import static main.model.logistic.CargoStatus.PREPARED;
import static main.model.users.DriverStatus.ON_DUTY_DRIVING;

public class CargoCheckProvider {
    private static final String STATUS_UPDATE_ERR = "Cargo status can be changed in order PREPARED->TRANSPORTING->DELIVERED only!";
    private static final String STATUS_DID_NOT_CHANGED = "New status is the same as the previous one!";
    private static final String NOBODY_IS_DRIVING = "There is no driver with status ON_DUTY_DRIVING!";
    private static final String NPE = "No cargos found!!";


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

    public void exists(Cargo cargo){
        if(cargo==null) throw new IllegalArgumentException(NPE);
    }
}
