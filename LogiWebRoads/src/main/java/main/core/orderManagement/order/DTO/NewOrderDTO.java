package main.core.orderManagement.order.DTO;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class NewOrderDTO {
    private List<DeliveryObject> deliveryObjects;
}
