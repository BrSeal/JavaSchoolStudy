package main.configuration;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.TopicConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

@Configuration
public class MessagingConfig {

    private final static String WILDFLY_REMOTING_URL="http-remoting://127.0.0.1:8080";
    private static final String DEFAULT_BROKER_URL = "tcp://localhost:61616";
    private static final String TOPIC_NAME = "logiweb";

    @Bean
    public TopicConnectionFactory topicConnectionFactory() throws NamingException {
        Hashtable <String,String> props=new Hashtable<>();
        props.put(Context.INITIAL_CONTEXT_FACTORY,"org.wildfly.naming.client.WildFlyInitialContextFactory");
        props.put(Context.PROVIDER_URL,WILDFLY_REMOTING_URL);
        props.put(Context.SECURITY_PRINCIPAL,"admin");
        props.put(Context.SECURITY_CREDENTIALS,"admin");

        Context context=new InitialContext(props);

        return (TopicConnectionFactory) context.lookup(Context.INITIAL_CONTEXT_FACTORY);
    }

    @Bean
    public JmsTemplate jmsTemplate() throws NamingException {
        JmsTemplate jmsTemplate = new JmsTemplate();
        jmsTemplate.setConnectionFactory(topicConnectionFactory());
        jmsTemplate.setDefaultDestinationName(TOPIC_NAME);
        jmsTemplate.setPubSubDomain(true);
        return jmsTemplate;
    }
}