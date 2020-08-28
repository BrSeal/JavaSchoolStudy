package main.configuration;

import main.core.cargo.services.CargoCheckProvider;
import main.core.cargo.services.CargoLogic;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.global.exceptionHandling.NullChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargoConfig {
    @Bean
    public CargoCheckProvider cargoCheckProvider() {
        return new CargoCheckProvider();
    }

    @Bean
    public CargoLogic cargoLogic(CargoCheckProvider cargoCheckProvider, OrderCheckProvider orderCheckProvider, NullChecker nullChecker) {
        return new CargoLogic(cargoCheckProvider,orderCheckProvider,nullChecker);
    }
}
