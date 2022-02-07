package com.orderbook.websocket;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;
import org.json.JSONArray;  
import javax.annotation.PostConstruct;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.orderbook.service.OrderBookService;

import antlr.collections.List;

@Service
@DependsOn("servletServerContainerFactoryBean")
public class WebSocketClient {
	private WebSocketContainer container;
	private OrderBookEndpoint orderBookEndpoint;
	private Gson gson = new Gson();
	
	@Autowired
	private OrderBookService orderBookService;
	
	@PostConstruct
	private void onInit() throws DeploymentException, IOException, URISyntaxException {
		System.out.println("Initializingggg");
//			this.container = ContainerProvider.getWebSocketContainer();
//			this.orderBookEndpoint = new OrderBookEndpoint();
//			Session session = this.container.connectToServer(this.orderBookEndpoint, new URI("wss://ws-feed.exchange.coinbase.com"));
//			System.out.println("Connected....");
//			
//			// subscribing to level2 channell
//			
//			String payload = "{\n" + 
//				"    \"type\": \"subscribe\",\n" + 
//				"    \"channels\": [{ \"name\": \"level2\", \"product_ids\": [\"BTC-USD\"] }]\n" + 
//				"}";
//		session.getBasicRemote().sendText(payload);
		
		final OrderBookEndpoint clientEndPoint = new OrderBookEndpoint(new URI("wss://ws-feed.exchange.coinbase.com"));
		clientEndPoint.addMessageHandler(new OrderBookEndpoint.MessageHandler() {
            public void handleMessage(String message) {
                if(message.contains("snapshot")) {
                	ObjectMapper objectMapper = new ObjectMapper();
                	//String json = gson.toJson(message);
                	String json = "{\n" + 
                			"    \"type\": \"snapshot\",\n" + 
                			"    \"product_id\": \"BTC-USD\",\n" + 
                			"    \"bids\": [[\"10101.10\", \"0.45054140\"]],\n" + 
                			"    \"asks\": [[\"10102.55\", \"0.57753524\"]]\n" + 
                			"}";
                	try {
                		Map<String, Object> orderBookSnapshot = objectMapper.readValue(json, new TypeReference<Map<String,Object>>(){});
                		System.out.println(orderBookSnapshot);
                		String bids = orderBookSnapshot.get("bids").toString();
                		JSONArray array = new JSONArray(bids);  
                		array.forEach(orderBookService::HandleBids);
                		String asks = orderBookSnapshot.get("asks").toString();
                		JSONArray asksArray = new JSONArray(bids);  
                		asksArray.forEach(orderBookService::HandleAsks);
                		
                	} catch (Exception  e) {
                		System.out.println(e);
                	}
                	
                }
            }
        });	
		String payload = "{\n" + 
				"    \"type\": \"subscribe\",\n" + 
				"    \"channels\": [{ \"name\": \"level2\", \"product_ids\": [\"BTC-USD\"] }]\n" + 
				"}";
		clientEndPoint.sendMessage(payload);

        // wait 5 seconds for messages from websocket
       
			
	}
}
