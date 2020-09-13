package main.global.messaging;

import main.core.driver.entity.Driver;
import main.core.orderManagement.order.entity.Order;
import main.core.vehicle.entity.Vehicle;

import java.util.Calendar;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MonthlyInfoHolder {
    private int currentMonth;

    private List<Driver> drivers;
    private List<Order> orders;
    private List<Vehicle> vehicles;

    private void monthCheck(){
        int currentMonth=Calendar.getInstance().get(Calendar.MONTH)+1;
    }

    public void addDriver(Driver d){
        drivers.add(d);
    }
}
