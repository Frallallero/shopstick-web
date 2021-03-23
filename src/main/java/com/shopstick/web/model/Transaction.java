package com.shopstick.web.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Data;

@Data
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 312542631971985965L;
	private Integer userId;
	private String userName;
	private Integer cartId;
	private String cardName;
	private String cardSurname;
	private String cardNumber;
	private String cvv;
	private BigDecimal total;
	private String error;
}