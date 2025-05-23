package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.driver.DTO.BoardDriverDTO;
import main.core.driver.entity.Driver;
import main.core.orderManagement.order.entity.Order;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardOrderDTO {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

 private int id;
 private String status;
 private String creationDate;
 private String lastModifyDate;
 String vehicleRegNum;
 List<BoardDriverDTO> drivers;

 public BoardOrderDTO(Order order){
     id=order.getId();
     status=order.getStatus().name();
     creationDate=dateFormat.format(order.getCreationDate());
     lastModifyDate=dateFormat.format(new Date());
     vehicleRegNum=order.getAssignedVehicle()==null?"None":order.getAssignedVehicle().getRegNumber();
     if (order.getAssignedDrivers() == null) {
         drivers = new ArrayList<>();
     } else {
         drivers = order.getAssignedDrivers().stream()
                 .map(BoardDriverDTO::new)
                 .collect(Collectors.toList());
     }
 }
}
