package main.configuration.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.LogoutConfigurer;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private static final String[] PERMIT_ALL_URLS = {"/resources/**", "/board/**"};
    private static final String[] USER_URLS = {"/", "/about", "/home", "/city/**", "/cargo/**"};
    private static final String[] ADMIN_URLS = {"/employees/**", "/adminPage/**"};
    private static final String[] DRIVER_URLS = {"/driverDesk/**", "/driver/info/**", "/driver/updateStatus/**"};
    private static final String[] EMPLOYEE_URLS = {"/employeeDesk/**", "/driver/**", "/vehicle/**", "/order/**"};

    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String ADMIN = "ADMIN";
    private static final String DRIVER = "DRIVER";
    private static final String USER = "USER";

    private final DataSource dataSource;
    private final PasswordEncoder passwordEncoder;

    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(passwordEncoder);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.authorizeHttpRequests(requests -> requests
                        .requestMatchers(PERMIT_ALL_URLS).permitAll()
                        .requestMatchers(USER_URLS).hasRole(USER)
                        .requestMatchers(ADMIN_URLS).hasRole(ADMIN)
                        .requestMatchers(DRIVER_URLS).hasRole(DRIVER)
                        .requestMatchers(EMPLOYEE_URLS).hasRole(EMPLOYEE)
                )
                .formLogin(form -> form.loginPage("/loginPage")
                        .loginProcessingUrl("/authenticate")
                        .permitAll()
                )
                .logout(LogoutConfigurer::permitAll)
                .exceptionHandling(handler -> handler.accessDeniedPage("/accessDenied"))
                .build()
                ;
    }
}
