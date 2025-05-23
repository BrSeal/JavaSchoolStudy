package main.core.vehicle.services;

import main.core.cityAndRoads.cities.entity.City;
import main.core.vehicle.DTO.NewVehicleDTO;
import main.core.vehicle.DTO.VehicleFullInfoDTO;
import main.core.vehicle.entity.Vehicle;
import main.global.exceptionHandling.exceptions.DeletionFailException;
import main.global.exceptionHandling.exceptions.NotFoundException;
import main.global.exceptionHandling.exceptions.SaveFailedException;
import main.global.exceptionHandling.exceptions.UpdateFailException;

import java.util.List;

public class VehicleCheckProvider {
    private static final String REGNUM_PATTERN= "^[A-Z]\\d{3}[A-Z]{2}$";
    private static final String REG_NUMBER_FORMAT_ERR = "Registration number format error!";
    private static final String NOT_UNIQUE_REGNUM = "Can't save new vehicle! Registration number %s already presented in database!";
    private static final String WRONG_DUTY_SIZE = "Can't save new vehicle! Duty size must be from 1 to 4!";
    private static final String WRONG_CAPACITY = "Can't save new vehicle! Capacity must be from 100 to 4000!";
    private static final String DELETE_ERR = "Deletion failed! Vehicle #%d is currently on order #%d! Vehicle can be deleted after finishing current order!";

    private static final String ON_ORDER_DUTY_SIZE = "Duty size can't be updated while vehicle is assigned on order!";
    private static final String ON_ORDER_CAPACITY = "Capacity can't be updated while vehicle is assigned on order!";
    private static final String ON_ORDER_CITY = "Location can't be updated while vehicle is assigned on order!";
    private static final String ORDER_UPDATE_ERR = "You cant reassign vehicle on order!";
    private static final String ALREADY_ASSIGNED_ERR = "Vehicle #%d is already assigned to order #%d!";
    private static final String BROKEN_VEHICLE_ERR = "Vehicle #%d is broken and can't be used!";
    private static final String LOW_CAPACITY_ERR = "Capacity of the vehicle #%d is too low! Need at least %d.";
    private static final String LOW_DUTY_SIZE = "Vehicle #%d has too little duty size to be assigned!";

    public void canBeUpdated(Vehicle vehicle, VehicleFullInfoDTO dto, List<String> regNums) {
        String regNumFromDTO=dto.getRegNumber();
        int orderIdFromDto=dto.getCurrentOrder();
        int dutySizeFromDto=dto.getDutySize();
        int capacityFromDto=dto.getCapacity();
        int cityFromDto=dto.getCurrentCityId();

        if(!vehicle.getRegNumber().equals(regNumFromDTO)) {
            regNumCheck(regNumFromDTO, regNums);
        }

        if(vehicle.getCurrentOrder()!=null){
            if(dutySizeFromDto!=0&&dutySizeFromDto!=vehicle.getDutySize()) {
                throw new UpdateFailException(ON_ORDER_DUTY_SIZE);
            }
            if(capacityFromDto!=0&&capacityFromDto!=vehicle.getCapacity()) {
                throw new UpdateFailException(ON_ORDER_CAPACITY);
            }
            if(cityFromDto!=0&&cityFromDto!=vehicle.getCurrentCity().getId()) {
                throw new UpdateFailException(ON_ORDER_CITY);
            }
            if(orderIdFromDto!=0&&orderIdFromDto!=vehicle.getCurrentOrder().getId()) {
                throw new UpdateFailException(ORDER_UPDATE_ERR);
            }
        }
    }

    public void canBeDeleted(Vehicle vehicle) {
        boolean canBeDeleted = vehicle.getCurrentOrder() == null ;

        if (!canBeDeleted) {
            int id = vehicle.getId();
            int orderId = vehicle.getCurrentOrder().getId();
            throw new DeletionFailException(String.format(DELETE_ERR, id, orderId));
        }
    }

    public void validateNew(NewVehicleDTO dto, List<String> regNums, List<Integer> cities) {

       regNumCheck(dto.getRegNumber(),regNums);

        if (dto.getDutySize() <= 0 || dto.getDutySize() > 4) {
            throw new SaveFailedException(WRONG_DUTY_SIZE);
        }

        if (dto.getCapacity() <= 100 || dto.getCapacity() > 4000) {
            throw new SaveFailedException(WRONG_CAPACITY);
        }

        int cityId = dto.getCurrentCityId();

        if (!cities.contains(cityId)) throw new NotFoundException(City.class, cityId);
    }

    private void regNumCheck(String regNumber, List<String> regNums){
        if(!regNumber.matches(REGNUM_PATTERN)) throw new UpdateFailException(REG_NUMBER_FORMAT_ERR);

        if (regNums.contains(regNumber)) {
            String errMsg = String.format(NOT_UNIQUE_REGNUM, regNumber);
            throw new UpdateFailException(errMsg);
        }
    }

    public void canBeAssigned(Vehicle vehicle, int minDutySize, int maxLoad){
        int capacity = vehicle.getCapacity();

        if(minDutySize>vehicle.getDutySize()) {
            String errMsg=String.format(LOW_DUTY_SIZE, vehicle.getId());
            throw new UpdateFailException(errMsg);
        }

        if (!vehicle.isOk()) {
            String errMsg = String.format(BROKEN_VEHICLE_ERR, vehicle.getId());
            throw new UpdateFailException(errMsg);
        }

        if (vehicle.getCurrentOrder() != null) {
            String errMsg = String.format(ALREADY_ASSIGNED_ERR, vehicle.getId(), vehicle.getCurrentOrder().getId());
            throw new UpdateFailException(errMsg);
        }

        if (capacity < maxLoad) {
            throw new UpdateFailException(String.format(LOW_CAPACITY_ERR, vehicle.getId(), maxLoad));
        }
    }

    public void isOk(Vehicle vehicle) {
        if(!vehicle.isOk()) throw new UpdateFailException(BROKEN_VEHICLE_ERR);
    }
}
