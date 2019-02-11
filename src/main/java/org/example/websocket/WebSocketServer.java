package org.example.websocket;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.lang.invoke.MethodHandles;

@ServerEndpoint("/ws/{id}")
public class WebSocketServer {
    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @OnOpen
    public void connect(Session session, @PathParam("id") String id) {
        logger.info("Open new websocket session (session id: {}, id: {})", session.getId(), id);
    }


    @OnMessage
    public void processMessage(String message, Session session, @PathParam("id") String id) throws IOException {
        logger.info("Message received from client: {} (session id: {}, id: {})", message, session.getId(), id);
        session.getBasicRemote().sendText(message);
    }

    @OnClose
    public void close(Session session, @PathParam("id") String id) {
        logger.info("Close session (session id: {}, id: {})", session.getId(), id);
    }
}
