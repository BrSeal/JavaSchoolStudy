package main;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.inject.Inject;

@MessageDriven
        (name = "logiweb",
                activationConfig = {
                                @ActivationConfigProperty(propertyName = "destinationLookup", propertyValue = "topic/logiweb"),
                                @ActivationConfigProperty(propertyName = "destinationType", propertyValue = "javax.jms.Topic"),
                                @ActivationConfigProperty(propertyName = "acknowledgeMode", propertyValue = "Auto-acknowledge")
                        }
        )
public class MyMessageListener implements MessageListener{

    @Inject
    InfoContainer infoContainer;

    @Override
    public void onMessage(Message message) {


        System.out.println("Got message");
    }
}
