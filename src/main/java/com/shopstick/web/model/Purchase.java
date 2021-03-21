package com.shopstick.web.model;

import lombok.Data;

@Data
public class Purchase {
	private Integer cartId;
	private String cardName;
	private String cardSurname;
	private String cardNumber;
	private String cvv;
}
