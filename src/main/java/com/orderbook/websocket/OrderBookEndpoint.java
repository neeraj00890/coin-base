package com.orderbook.websocket;

import java.io.IOException;
import java.net.URI;
import java.nio.ByteBuffer;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.Endpoint;
import javax.websocket.EndpointConfig;
import javax.websocket.MessageHandler;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

@ClientEndpoint
public class OrderBookEndpoint {
	 Session userSession = null;
	    private MessageHandler messageHandler;

	    public OrderBookEndpoint(URI endpointURI) {
	        try {
	            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
	            container.connectToServer(this, endpointURI);
	        } catch (Exception e) {
	            throw new RuntimeException(e);
	        }
	    }
	    
	    @OnOpen
	    public void onOpen(Session userSession) {
	        System.out.println("opening websocket");
	        userSession.setMaxBinaryMessageBufferSize(394854554);
	        userSession.setMaxTextMessageBufferSize(394854554);
	        this.userSession = userSession;
	    }

	    @OnClose
	    public void onClose(Session userSession, CloseReason reason) {
	        System.out.println("closing websocket");
	        this.userSession = null;
	    }

	    
	    @OnMessage
	    public void onMessage(String message) {
	        if (this.messageHandler != null) {
	            this.messageHandler.handleMessage(message);
	        }
	    }

	   @OnMessage
	   public void onMessage(ByteBuffer bytes) {
	        System.out.println("Handle byte buffer");
	    }
	
	    public void addMessageHandler(MessageHandler msgHandler) {
	        this.messageHandler = msgHandler;
	    }

	    public void sendMessage(String message) {
	        this.userSession.getAsyncRemote().sendText(message);
	    }

	
	    public static interface MessageHandler {

	        public void handleMessage(String message);
	    }

}
