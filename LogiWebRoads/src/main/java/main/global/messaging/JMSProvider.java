package main.global.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;

@Component
public class JMSProvider {
    private final JmsTemplate jmsTemplate;
    private final TopicConnectionFactory connectionFactory;

    @Autowired
    public JMSProvider(JmsTemplate jmsTemplate, TopicConnectionFactory connectionFactory) {
        this.jmsTemplate = jmsTemplate;
        this.connectionFactory = connectionFactory;
    }

    public void sendMessage() throws JMSException {
        TopicConnection connection=connectionFactory.createTopicConnection("admin","admin");
        connection.start();

        jmsTemplate.send(s->s.createTextMessage("Hello from JMSProvider!"));

        connection.close();


        //jmsTemplate.send(session -> session.createTextMessage("Hello from JMSProvider!"));
    }
}
