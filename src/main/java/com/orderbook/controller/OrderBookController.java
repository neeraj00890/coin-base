package com.orderbook.controller;

import java.util.Arrays;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OrderBookController {

	@GetMapping("/order-books")
	public Object getOrder() {
		return Arrays.asList("Test", "App");
	}
}

