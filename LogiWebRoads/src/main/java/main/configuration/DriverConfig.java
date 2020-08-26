package main.configuration;

import main.core.driver.services.DriverCheckProvider;
import main.core.driver.services.DriverLogic;
import main.core.order.services.OrderCheckProvider;
import main.core.order.services.OrderLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverConfig {
    @Bean
    public DriverCheckProvider driverCheckProvider(){
        return new DriverCheckProvider();
    }

    @Bean
    public DriverLogic driverLogic(DriverCheckProvider provider, OrderCheckProvider orderCheckProvider, OrderLogic orderLogic){

        return new DriverLogic(provider,orderCheckProvider,orderLogic);
    }
}
