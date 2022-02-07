package com.orderbook.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class OrderBook {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long id;
	
	@Column(name = "PRODUCT_ID")
	public String productId;
	
	@Column(name = "BUY_SIZE")
	public BigDecimal buySize;
	
	@Column(name = "BUY_PRICE")
	public BigDecimal buyPrice;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public BigDecimal getBuySize() {
		return buySize;
	}

	public void setBuySize(BigDecimal buySize) {
		this.buySize = buySize;
	}

	public BigDecimal getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(BigDecimal buyPrice) {
		this.buyPrice = buyPrice;
	}

	public BigDecimal getSellSize() {
		return sellSize;
	}

	public void setSellSize(BigDecimal sellSize) {
		this.sellSize = sellSize;
	}

	public BigDecimal getSellPrice() {
		return sellPrice;
	}

	public void setSellPrice(BigDecimal sellPrice) {
		this.sellPrice = sellPrice;
	}

	@Column(name = "SELL_SIZE")
	public BigDecimal sellSize;
	
	@Column(name = "SEll_PRICE")
	public BigDecimal sellPrice;
	
	
}
