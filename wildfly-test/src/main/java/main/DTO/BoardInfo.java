package main.DTO;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BoardInfo {
    private List<Order> orders;

    private int driversCount;
    private int driversOnOrderCount;

    private int vehiclesCount;
    private int vehiclesOnOrder;
    private int vehiclesOkCount;
}
