package main.configuration;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import main.configuration.security.UserConfig;
import main.global.exceptionHandling.NullChecker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@ComponentScan(basePackages = "main")
@Import({
        CargoConfig.class,
        DriverConfig.class,
        OrderConfig.class,
        VehicleConfig.class,
        UserConfig.class,
        EmployeeConfig.class
})
public class MainConfig implements WebMvcConfigurer {
    private static final String HIBERNATE_DDL_AUTO  = "create";

    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }

    @Bean
    public DataSource myDataSource() {
        ComboPooledDataSource cpds = new ComboPooledDataSource();

        try {
            cpds.setDriverClass("com.mysql.cj.jdbc.Driver");


        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        cpds.setJdbcUrl("jdbc:mysql://localhost:3306/logiweb?useSSL=false&serverTimezone=UTC");
        cpds.setUser("root");
        cpds.setPassword("root");

        cpds.setMaxPoolSize(10);
        cpds.setMinPoolSize(3);
        cpds.setMaxIdleTime(3000);

        return cpds;
    }

    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean factory = new LocalSessionFactoryBean();
        factory.setDataSource(myDataSource());
        factory.setPackagesToScan("main.core");

        Properties hibernateProps = new Properties();
        hibernateProps.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
        hibernateProps.put("hibernate.hbm2ddl.auto", HIBERNATE_DDL_AUTO);
        hibernateProps.put("hibernate.show_sql", "true");

        factory.setHibernateProperties(hibernateProps);

        return factory;
    }

    @Bean
    public HibernateTransactionManager myTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory().getObject());
        return transactionManager;
    }

    @Bean
    public NullChecker nullChecker() {
        return new NullChecker();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }
}
