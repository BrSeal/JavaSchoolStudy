package main.configuration;

import main.core.order.services.OrderCheckProvider;
import main.core.order.services.OrderLogic;
import main.core.waypoint.services.WaypointCheckProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Bean
    public OrderCheckProvider orderCheckProvider(){
        return new OrderCheckProvider();
    }
    @Bean
    public OrderLogic orderLogic(){
        return new OrderLogic();
    }

    @Bean
    public WaypointCheckProvider waypointCheckProvider(){
        return new WaypointCheckProvider();
    }
}
