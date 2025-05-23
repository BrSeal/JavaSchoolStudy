package main.core.vehicle.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.entity.Order;
import main.global.mappedSuperclass.IdClass;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle extends IdClass {

    @Column(name = "reg_number", unique = true)
    private String regNumber;

    @Column(name = "duty_size")
    private int dutySize;

    @Column(name = "capacity")
    private int capacity;

    @Column(name = "is_ok")
    private boolean ok;

    @ManyToOne
    @JoinColumn(name = "current_city_id")
    private City currentCity;

    @OneToOne
    @JoinColumn(name = "current_order_id")
    private Order currentOrder;

    
}
