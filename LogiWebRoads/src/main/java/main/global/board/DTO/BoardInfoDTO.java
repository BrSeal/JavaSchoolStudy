package main.global.board.DTO;

import main.core.orderManagement.order.DTO.BoardOrderDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class BoardInfoDTO {
    private List<BoardOrderDTO> orders;

    private int driversCount;
    private int driversOnOrderCount;

    private int vehiclesCount;
    private int vehiclesOnOrder;
    private int vehiclesOkCount;

    public BoardInfoDTO(HashMap<Integer,BoardOrderDTO> orders, int driversCount, int driversOnOrderCount, int vehiclesCount, int vehiclesOnOrder, int vehiclesOkCount) {
        this.orders = new ArrayList<>(orders.values());
        this.driversCount = driversCount;
        this.driversOnOrderCount = driversOnOrderCount;
        this.vehiclesCount = vehiclesCount;
        this.vehiclesOnOrder = vehiclesOnOrder;
        this.vehiclesOkCount = vehiclesOkCount;
    }
}
