package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.driver.DTO.MessageDriverDTO;
import main.core.orderManagement.order.entity.Order;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MessageOrderDTO {
    private static SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

 private int id;
 private String status;
 private String creationDate;
 private String lastModifyDate;
 String vehicleRegNum;
 List<MessageDriverDTO> drivers;

 public MessageOrderDTO(Order order){
     id=order.getId();
     status=order.getStatus().name();
     creationDate=dateFormat.format(order.getCreationDate());
     lastModifyDate=dateFormat.format(new Date());
     vehicleRegNum=order.getAssignedVehicle()==null?"None":order.getAssignedVehicle().getRegNumber();
     drivers=order.getAssignedDrivers().stream().map(MessageDriverDTO::new).collect(Collectors.toList());
 }
}
