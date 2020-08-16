package main.core.cargo.services;

import main.model.logistic.Cargo;
import main.model.logistic.CargoStatus;
import main.model.logistic.Waypoint;

import java.util.List;

import static main.model.logistic.CargoStatus.*;
import static main.model.logistic.WaypointType.LOAD;
import static main.model.logistic.WaypointType.UNLOAD;

public class CargoUpdateProcessor {
    private static final String STATUS_UPDATE_ERR = "Cargo status can be changed in order PREPARED->TRANSPORTING->DELIVERED only!";
    private static final String STATUS_DID_NOT_CHANGED = "New status is the same as the previous one!";

    public static void updateStatusLogic(Cargo cargo, Cargo dto, List<Waypoint> waypoints) {
        CargoStatus cargoStatus = cargo.getStatus();
        CargoStatus dtoStatus = dto.getStatus();

        updateCheck(cargoStatus,dtoStatus);

        cargo.setStatus(dtoStatus);
        if(dtoStatus==TRANSPORTING){
            waypoints.forEach(w->{
                if(w.getType()== LOAD) w.setDone(true);
            });
        }else if(dtoStatus==DELIVERED){
            waypoints.forEach(w->{
                if(w.getType()== UNLOAD) {
                    w.setDone(true);
                    if(w.getPathLength()==0){
                        w.getOrder().setCompleted(true);
                    }
                }
            });
        }
    }

    private static void updateCheck(CargoStatus cargoStatus,CargoStatus dtoStatus){
        boolean isDowngradingOrJumping =
                (cargoStatus != PREPARED && dtoStatus == PREPARED)
                        ||
                        (cargoStatus == DELIVERED && dtoStatus != DELIVERED)
                        ||
                        (cargoStatus==PREPARED&&dtoStatus==DELIVERED);


        if (isDowngradingOrJumping) throw new IllegalArgumentException(STATUS_UPDATE_ERR);

        if(cargoStatus==dtoStatus) throw new IllegalArgumentException(STATUS_DID_NOT_CHANGED);
    }
}
