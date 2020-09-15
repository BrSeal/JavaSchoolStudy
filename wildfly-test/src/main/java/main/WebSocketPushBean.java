package main;

import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.push.Push;
import javax.faces.push.PushContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@Named
@Startup
@ApplicationScoped
public class WebSocketPushBean implements Serializable {

    @Inject
    @Push(channel = "websocket")
    private PushContext pushContext;

    public void sendMessage() {
        pushContext.send("update");
    }
}
