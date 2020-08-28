package main.configuration;

import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.orderManagement.order.services.OrderLogic;
import main.core.orderManagement.waypoint.services.WaypointCheckProvider;
import main.global.exceptionHandling.NullChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Bean
    public OrderCheckProvider orderCheckProvider(){
        return new OrderCheckProvider();
    }
    @Bean
    public OrderLogic orderLogic(NullChecker nullChecker){
        return new OrderLogic(nullChecker);
    }

    @Bean
    public WaypointCheckProvider waypointCheckProvider(){
        return new WaypointCheckProvider();
    }
}
