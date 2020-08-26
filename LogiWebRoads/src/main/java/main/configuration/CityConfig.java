package main.configuration;

import main.core.cityAndRoads.cities.services.CityCheckProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CityConfig {
    @Bean
    public CityCheckProvider cityCheckProvider() {
        return new CityCheckProvider();
    }
}
