package main.configuration;

import main.core.employee.services.EmployeeCheckProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EmployeeConfig {

    @Bean
   public EmployeeCheckProvider employeeCheckProvider(){
       return  new EmployeeCheckProvider();
   }
}
