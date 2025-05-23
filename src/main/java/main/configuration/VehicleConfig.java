package main.configuration;

import main.core.vehicle.services.VehicleCheckProvider;
import main.core.vehicle.services.VehicleLogic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class VehicleConfig {
    @Bean
    public VehicleCheckProvider vehicleCheckProvider(){
        return new VehicleCheckProvider();
    }

    @Bean
    public VehicleLogic vehicleLogic(){
        return new VehicleLogic();
    }
}
