package main.core.vehicle.services;

import main.core.cityAndRoads.cities.entity.City;
import main.core.vehicle.DTO.VehicleFullInfoDTO;
import main.core.vehicle.entity.Vehicle;

public class VehicleLogic {
    public void updateFields(Vehicle vehicle, VehicleFullInfoDTO dto){
        int cityId=dto.getCurrentCityId();
        int capacity=dto.getCapacity();
        int dutySize=dto.getDutySize();

        vehicle.setRegNumber(dto.getRegNumber());
        vehicle.setOk(dto.isOk());
        if(cityId!=0){
            City city=new City();
            city.setId(cityId);
            vehicle.setCurrentCity(city);
        }
        if(capacity!=0) vehicle.setCapacity(capacity);
        if(dutySize!=0) vehicle.setDutySize(dutySize);
    }
}
