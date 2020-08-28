package main.core.vehicle.services;

import main.core.cityAndRoads.cities.entity.City;
import main.core.vehicle.DTO.NewVehicleDTO;
import main.core.vehicle.DTO.VehicleDTO;
import main.core.vehicle.entity.Vehicle;
import main.global.exceptionHandling.exceptions.DeletionFailedException;
import main.global.exceptionHandling.exceptions.NotFoundException;
import main.global.exceptionHandling.exceptions.SaveFailedException;
import main.global.exceptionHandling.exceptions.UpdateFailException;

import java.util.List;
import java.util.Objects;

public class VehicleCheckProvider {
    private static final String REGNUM_PATTERN= "^[A-Z]\\d{3}[A-Z]{2}$";
    private static final String REG_NUMBER_FORMAT_ERR = "Registration number format error!";
    private static final String NOT_UNIQUE_REGNUM = "Can't save new vehicle! Registration number %s already presented in database!";
    private static final String WRONG_DUTY_SIZE = "Can't save new vehicle! Duty size must be from 1 to 4!";
    private static final String WRONG_CAPACITY = "Can't save new vehicle! Duty size must be from 1 to 4!";
    private static final String DELETE_ERR = "Deletion failed! Vehicle #%d is currently on order #%d! Vehicle can be deleted after finishing current order!";

    private static final String UPDATE_FAIL_ON_ORDER_DUTY_SIZE = "Duty size can't be updated while vehicle is assigned on order!";
    private static final String UPDATE_FAIL_ON_ORDER_CAPACITY = "Capacity can't be updated while vehicle is assigned on order!";
    private static final String UPDATE_FAIL_ON_ORDER_CITY = "Location can't be updated while vehicle is assigned on order!";
    private static final String UPDATE_FAIL_ORDER_UPDATE_ERR = "You cant reassign vehicle!";

    public void canBeUpdated(Vehicle vehicle, VehicleDTO dto) {
        if(Objects.nonNull(vehicle.getCurrentOrder())){
            if(dto.getDutySize()!=0) throw new UpdateFailException(UPDATE_FAIL_ON_ORDER_DUTY_SIZE);
            if(dto.getCapacity()!=0) throw new UpdateFailException(UPDATE_FAIL_ON_ORDER_CAPACITY);
            if(dto.getCurrentCityId()!=0) throw new UpdateFailException(UPDATE_FAIL_ON_ORDER_CITY);
            if(dto.getCurrentOrder()!=0) throw new UpdateFailException(UPDATE_FAIL_ON_ORDER_CAPACITY);
        }

        if(dto.getCurrentOrder()!=0) throw new UpdateFailException(UPDATE_FAIL_ORDER_UPDATE_ERR);



    }

    public void canBeDeleted(Vehicle vehicle) {
        boolean canBeDeleted = vehicle.getCurrentOrder() == null ;

        if (!canBeDeleted) {
            int id = vehicle.getId();
            int orderId = vehicle.getCurrentOrder().getId();
            throw new DeletionFailedException(String.format(DELETE_ERR, id, orderId));
        }
    }

    public void validateNew(NewVehicleDTO dto, List<String> regNums, List<Integer> cities) {

        if(!dto.getRegNumber().matches(REGNUM_PATTERN)) throw new SaveFailedException(REG_NUMBER_FORMAT_ERR);

        if (regNums.contains(dto.getRegNumber())) {
            String errMsg = String.format(NOT_UNIQUE_REGNUM, dto.getRegNumber());
            throw new SaveFailedException(errMsg);
        }

        if (dto.getDutySize() <= 0 || dto.getDutySize() > 4) {
            throw new SaveFailedException(WRONG_DUTY_SIZE);
        }

        if (dto.getCapacity() <= 100 || dto.getCapacity() > 4000) {
            throw new SaveFailedException(WRONG_CAPACITY);
        }

        int cityId = dto.getCurrentCityId();

        if (!cities.contains(cityId)) throw new NotFoundException(City.class, cityId);
    }
}
