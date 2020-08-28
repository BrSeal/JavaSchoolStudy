package main.core.orderManagement.order.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.cargo.DTO.NewCargoDTO;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliveryObject {
    private NewCargoDTO cargo;
    private int cityIdFrom;
    private int cityIdTo;
}
