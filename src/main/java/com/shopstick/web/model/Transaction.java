package com.shopstick.web.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Transaction implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 312542631971985965L;
	private Integer cartId;
	private String cardName;
	private String cardSurname;
	private String cardNumber;
	private String cvv;
}