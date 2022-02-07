package com.orderbook.service;

import java.math.BigDecimal;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.orderbook.entity.OrderBook;
import com.orderbook.repository.OrderBookRepository;

@Service
public class OrderBookService {

	@Autowired
	OrderBookRepository orderBookRepository;
	public void HandleBids(Object bids) {
		JSONArray bidsArray = new JSONArray(bids.toString());  
		OrderBook buyOrder = new OrderBook();
		buyOrder.setBuyPrice((BigDecimal)bidsArray.get(0));
		buyOrder.setBuySize((BigDecimal)bidsArray.get(1));
		buyOrder.setProductId("BTC-USD");
		orderBookRepository.save(buyOrder);
		System.out.println("Entity Saved");
		
	}
	
	public void HandleAsks(Object asks) {
		JSONArray askArray = new JSONArray(asks.toString());  
		OrderBook buyOrder = new OrderBook();
		buyOrder.setSellPrice((BigDecimal)askArray.get(0));
		buyOrder.setSellSize((BigDecimal)askArray.get(1));
		buyOrder.setProductId("BTC-USD");
		orderBookRepository.save(buyOrder);
		System.out.println("Entity Saved");
		
	}
	
}
