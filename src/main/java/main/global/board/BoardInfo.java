package main.global.board;

import main.core.orderManagement.order.DTO.BoardOrderDTO;
import main.core.orderManagement.order.entity.Order;
import main.global.board.DTO.BoardInfoDTO;
import main.global.exceptionHandling.exceptions.CountException;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.HashMap;

@Component
public class BoardInfo {

    private int currentMonth=Calendar.getInstance().get(Calendar.MONTH);
    private HashMap<Integer,BoardOrderDTO> orders = new HashMap<>();

    private int driversCount;
    private int driversOnOrderCount;

    private int vehiclesCount;
    private int vehiclesOnOrder;
    private int brokenVehiclesCount;

    public BoardInfoDTO toDto(){
        return new BoardInfoDTO(orders,driversCount,driversOnOrderCount,vehiclesCount,vehiclesOnOrder, brokenVehiclesCount);
    }

    public void addOrUpdateOrderInfo(Order order){
        monthUpdateIfNeeded();
        if(orders==null||orders.isEmpty()) {
            orders = new HashMap<>();
            orders.put(order.getId(), new BoardOrderDTO(order));
        }
    }

    public void addDriver(){
        driversCount++;
    }

    public void deleteDriver(){
        monthUpdateIfNeeded();
        if (driversCount<=0) throw new CountException("drivers");
        driversCount--;
    }

    public void addAssignedDriver(){
        monthUpdateIfNeeded();
        driversOnOrderCount++;
    }

    public void decrementAssignedDrivers(){
        monthUpdateIfNeeded();
        if (driversOnOrderCount<=0) throw new CountException("drivers");
        driversOnOrderCount--;
    }

    public void addVehicle(){
        monthUpdateIfNeeded();
        vehiclesCount++;
    }

    public void deleteVehicle(){
        monthUpdateIfNeeded();
        if (vehiclesCount<=0) throw new CountException("vehicles");
        vehiclesCount--;
    }

    public void addAssignedVehicle(){
        monthUpdateIfNeeded();
        vehiclesOnOrder++;
    }

    public void decrementVehiclesOnOrder(){
        monthUpdateIfNeeded();
        if (vehiclesOnOrder<=0) throw new CountException("vehicles");
        vehiclesOnOrder--;
    }

    public void incrementBrokenVehiclesCount(){
        monthUpdateIfNeeded();
        brokenVehiclesCount++;
    }

    public void decrementBrokenVehiclesCount(){
        monthUpdateIfNeeded();
        if (brokenVehiclesCount <=0) throw new CountException("vehicles");
        brokenVehiclesCount--;
    }

    public void updateRemoteBoard(){
        //TODO отправка сообщения по JMS
    }

    private void monthUpdateIfNeeded(){
        int current=Calendar.getInstance().get(Calendar.MONTH);
        if(current!=currentMonth){
            currentMonth=current;
            orders=new HashMap<>();
        }
    }
}
