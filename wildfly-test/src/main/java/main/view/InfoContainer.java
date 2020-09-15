package main.view;

import lombok.Getter;
import lombok.Setter;
import main.AMQConsumer;
import main.DTO.BoardInfo;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import java.io.IOException;
import java.io.Serializable;


@Getter
@Setter
@Named
@ApplicationScoped
public class InfoContainer implements Serializable {
    private static final String TARGET_URL="http://localhost:8181/LogiWeb_roads_war_exploded/board/";

    private final static ObjectMapper objectMapper = new ObjectMapper();
    private AMQConsumer consumer;

    @Inject
    public InfoContainer( AMQConsumer consumer) {
        this.consumer = consumer;
    }

    @PostConstruct
    public void init() {
        consumer.consume();
    }

    public BoardInfo getInfo() throws IOException {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(TARGET_URL);
        String response = target.request(MediaType.APPLICATION_JSON).get(String.class);
        return objectMapper.readValue(response, new TypeReference<BoardInfo>() {
        });
    }
}
