package main;

import lombok.Getter;
import lombok.Setter;
import main.DTO.*;
import org.codehaus.jackson.map.ObjectMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;

import java.io.Serializable;
import java.util.List;


@Getter
@Setter
@Named
@ApplicationScoped

public class InfoContainer implements Serializable {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private List<Order> orders;

    private int[] vehicleStats;
    private int[] driverStats;

    public InfoContainer() {

        Driver driver1 = new Driver(1, "Ivan", "Ivanov");
        Driver driver2 = new Driver(2, "Petr", "Petrov");
        Driver driver3 = new Driver(3, "Egor", "Egorov");
        Driver driver4 = new Driver(4, "Gleb", "Glebov");

        List<Driver> drivers1 = List.of(driver1, driver2);
        List<Driver> drivers2 = List.of(driver3);
        List<Driver> drivers3 = List.of(driver4);


        Order order1 = new Order(1, "01.02.20","01.02.20", OrderStatus.ASSIGNED,"A000AA", drivers1);
        Order order2 = new Order(2, "02.02.20", "02.02.20", OrderStatus.IN_PROGRESS, "A000AB",drivers2);
        Order order3 = new Order(3, "03.02.20", "03.02.20",OrderStatus.ASSIGNED, null,drivers3);

        orders = List.of(order1, order2, order3);


    }

}
