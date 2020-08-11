package main.model.logistic;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import main.model.IdClass;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order extends IdClass {

    @Column(name = "is_completed")
    private boolean isCompleted;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Waypoint> waypoints;
}
