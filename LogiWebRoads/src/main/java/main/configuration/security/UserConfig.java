package main.configuration.security;

import main.core.Security.UserCheckProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfig {
    @Bean
    public UserCheckProvider userCheckProvider() {
        return new UserCheckProvider();
    }

}
