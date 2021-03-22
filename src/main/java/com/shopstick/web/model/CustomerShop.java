package com.shopstick.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class CustomerShop implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 312542631971985965L;
	private String error;
	private Integer userId;
	private String userName;
	private Integer cartId;
	private Integer itemIdToAdd;
	private Integer itemToAddQuantity;
	private String category;
	private BigDecimal totalAmount;
	
	public CustomerShop() {
		totalAmount = BigDecimal.ZERO;
	}
	
}