package main.core.driver.entity;

import lombok.*;
import main.core.security.entity.User;
import main.core.cityAndRoads.cities.entity.City;
import main.core.orderManagement.order.entity.Order;
import main.global.mappedSuperclass.IdClass;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Table(name = "drivers")
public class Driver extends IdClass {

    @Column(name = "first_name",
            nullable = false,
            length = 50)
    @Size(min=5, max = 50)
    private String firstName;

    @Column(name = "last_name")
    @Size(min=5, max = 50)
    private String lastName;

    @Column(name = "hours_worked_current_month")
    @Min(0)
    @Max(176)
    private int hoursWorked;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private DriverStatus status;

    @ManyToOne
    @JoinColumn(name = "current_city_id")
    private City currentCity;

    @ManyToOne(optional=false)
    @JoinColumn(name = "current_order_id")
    @Nullable
    private Order currentOrder;

    @OneToOne
    private User user;
}


