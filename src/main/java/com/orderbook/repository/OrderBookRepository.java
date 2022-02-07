package com.orderbook.repository;

import org.springframework.data.repository.CrudRepository;

import com.orderbook.entity.OrderBook;

public interface OrderBookRepository extends CrudRepository<OrderBook, Long> {

}
