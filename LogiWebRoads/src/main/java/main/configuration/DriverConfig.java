package main.configuration;

import main.core.driver.services.DriverCheckProvider;
import main.core.driver.services.DriverLogic;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.orderManagement.order.services.OrderLogic;
import main.global.exceptionHandling.NullChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DriverConfig {
    @Bean
    public DriverCheckProvider driverCheckProvider(){
        return new DriverCheckProvider();
    }

    @Bean
    public DriverLogic driverLogic
            (
                    DriverCheckProvider driverCheckProvider,
                    OrderCheckProvider orderCheckProvider,
                    OrderLogic orderLogic,
                    NullChecker nullChecker
            ){
        return new DriverLogic(driverCheckProvider,orderCheckProvider,orderLogic,nullChecker);
    }
}
