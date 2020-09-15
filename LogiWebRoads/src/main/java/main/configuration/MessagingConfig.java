package main.configuration;

import org.apache.activemq.spring.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class MessagingConfig {

    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    private static final String TOPIC_NAME = "logiweb";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory();
        cf.setBrokerURL(DEFAULT_BROKER_URL);
        return cf;
    }

    @Bean
    public JmsTemplate jmsTemplate() {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(connectionFactory());
        jmsTemplate.setDefaultDestinationName(TOPIC_NAME);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
}