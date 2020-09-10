package main.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String EMPLOYEE = "EMPLOYEE";
    private static final String ADMIN = "ADMIN";
    private static final String DRIVER = "DRIVER";
    private static final String USER = "USER";

    private final DataSource dataSource;

    @Autowired
    public SecurityConfig(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(dataSource);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(
                        "/",
                        "/about",
                        "/home",
                        "/city/**",
                        "/cargo/**").hasRole(USER)
                .antMatchers(
                        "/employees/**",
                        "/adminPage/**").hasRole(ADMIN)
                .antMatchers(
                        "/driverDesk/**",
                        "/driver/info/**",
                        "/driver/updateStatus/**").hasRole(DRIVER)
                .antMatchers(
                        "/employeeDesk/**",
                        "/driver/**",
                        "/vehicle/**",
                        "/order/**").hasRole(EMPLOYEE)
                .and()
                .formLogin()
                .loginPage("/loginPage")
                .loginProcessingUrl("/authenticate")
                .permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");
    }
}
