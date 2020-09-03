package main.configuration;

import main.core.orderManagement.cargo.services.CargoCheckProvider;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.orderManagement.order.services.OrderLogic;
import main.global.exceptionHandling.NullChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Bean
    public OrderCheckProvider orderCheckProvider(CargoCheckProvider cargoCheckProvider){
        return new OrderCheckProvider(cargoCheckProvider);
    }
    @Bean
    public OrderLogic orderLogic(NullChecker nullChecker){
        return new OrderLogic(nullChecker);
    }
}
