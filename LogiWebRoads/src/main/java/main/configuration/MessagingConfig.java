package main.configuration;



import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import java.util.Hashtable;

@Configuration
public class MessagingConfig {

    private final static String WILDFLY_REMOTING_URL="http-remoting://127.0.0.1:8080";
    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    private static final String TOPIC_NAME = "logiweb";

    @Bean
    public ActiveMQConnectionFactory connectionFactory() {
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
        connectionFactory.setBrokerURL(DEFAULT_BROKER_URL);
        return connectionFactory;
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