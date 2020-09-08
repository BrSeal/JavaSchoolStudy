package main.core.driver.services;

import lombok.Getter;
import lombok.Setter;
import main.core.cityAndRoads.cities.entity.City;
import main.core.driver.DTO.DriverUpdateDTO;
import main.core.driver.entity.Driver;
import main.core.driver.entity.DriverStatus;
import main.core.orderManagement.order.entity.Order;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.orderManagement.order.services.OrderLogic;
import main.global.exceptionHandling.NullChecker;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
@Setter
public class DriverLogic {
    private static final String VEHICLE_NOT_ASSIGNED_ERR = "Vehicle is not assigned!";

    private DriverCheckProvider driverCheckProvider;
    private OrderCheckProvider orderCheckProvider;
    private OrderLogic orderLogic;
    private NullChecker nullChecker;

    @Autowired
    public DriverLogic(DriverCheckProvider driverCheckProvider,OrderCheckProvider orderCheckProvider,OrderLogic orderLogic,NullChecker nullChecker) {
        this.driverCheckProvider = driverCheckProvider;
        this.orderCheckProvider = orderCheckProvider;
        this.orderLogic = orderLogic;
        this.nullChecker=nullChecker;
    }

    public void updateDriver(Driver driver, DriverUpdateDTO dto){
        driverCheckProvider.canBeUpdated(driver,dto);

        driver.setFirstName(dto.getFirstName());
        driver.setLastName(dto.getLastName());
        driver.setHoursWorked(dto.getHoursWorked());

        int newCityId=dto.getCurrentCityId();
        if(newCityId!=0){
            City city=new City();
            city.setId(newCityId);
            driver.setCurrentCity(city);
        }
    }

    public void updateStatus(Order order, Driver driver, DriverStatus status) {
        driverCheckProvider.canUpdateStatus(order, driver, status);

        driver.setStatus(status);
    }
}
