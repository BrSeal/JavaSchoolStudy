package main.configuration;

import main.core.driver.services.DriverCheckProvider;
import main.core.orderManagement.cargo.services.CargoCheckProvider;
import main.core.orderManagement.cargo.services.CargoLogic;
import main.core.orderManagement.order.services.OrderCheckProvider;
import main.core.vehicle.services.VehicleCheckProvider;
import main.global.board.BoardInfo;
import main.global.exceptionHandling.NullChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CargoConfig {
    @Bean
    public CargoCheckProvider cargoCheckProvider(DriverCheckProvider driverCheckProvider, VehicleCheckProvider vehicleCheckProvider) {
        return new CargoCheckProvider(driverCheckProvider, vehicleCheckProvider);
    }

    @Bean
    public CargoLogic cargoLogic
            (
                    CargoCheckProvider cargoCheckProvider,
                    NullChecker nullChecker,
                    BoardInfo boardInfo
            ) {
        return new CargoLogic(cargoCheckProvider, nullChecker, boardInfo);
    }
}
