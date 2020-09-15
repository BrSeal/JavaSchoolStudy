package main;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.jms.Message;
import javax.jms.MessageListener;
import java.io.Serializable;

@Named
@ApplicationScoped
public class TopicSubscriber implements MessageListener, Serializable {

    @Inject
    private  WebSocketPushBean pushBean;

    @Override
    public void onMessage(Message message) {
        System.out.println("received message");
        pushBean.sendMessage();
    }
}