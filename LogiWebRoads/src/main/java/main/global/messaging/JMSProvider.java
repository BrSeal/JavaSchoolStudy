package main.global.messaging;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class JMSProvider {
    private final JmsTemplate jmsTemplate;

    @Autowired
    public JMSProvider(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;

        
    }

    public void sendMessage() {
        jmsTemplate.send(session -> session.createTextMessage("Hello from JMSProvider!"));
    }
}
