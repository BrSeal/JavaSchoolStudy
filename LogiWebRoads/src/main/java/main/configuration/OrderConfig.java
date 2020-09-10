package main.configuration;

import main.core.driver.services.DriverCheckProvider;
import main.core.orderManagement.cargo.services.CargoCheckProvider;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.orderManagement.order.services.OrderLogic;
import main.core.vehicle.services.VehicleCheckProvider;
import main.global.exceptionHandling.NullChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OrderConfig {
    @Bean
    public OrderCheckProvider orderCheckProvider
            (
                    CargoCheckProvider cargoCheckProvider,
                    VehicleCheckProvider vehicleCheckProvider,
                    DriverCheckProvider driverCheckProvider,
                    OrderLogic orderLogic
            ) {
        return new OrderCheckProvider(cargoCheckProvider, vehicleCheckProvider, driverCheckProvider, orderLogic);
    }

    @Bean
    public OrderLogic orderLogic(NullChecker nullChecker) {
        return new OrderLogic(nullChecker);
    }
}
