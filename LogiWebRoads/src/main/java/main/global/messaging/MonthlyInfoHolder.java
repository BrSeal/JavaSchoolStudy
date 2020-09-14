package main.global.messaging;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.driver.DriverRepository;
import main.core.orderManagement.order.DTO.MessageOrderDTO;
import main.core.vehicle.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class MonthlyInfoHolder {
    private final DriverRepository driverRepository;
    private final VehicleRepository vehicleRepository;

    @Autowired
    public MonthlyInfoHolder(DriverRepository driverRepository,VehicleRepository vehicleRepository){
        this.driverRepository=driverRepository;
        this.vehicleRepository=vehicleRepository;
    }

    private int currentMonth;
    List<MessageOrderDTO> orders;


    private void monthChangedLogic() {
        int currentMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
        if (this.currentMonth != currentMonth) {
            orders = new ArrayList<>();


        }


    }

}
