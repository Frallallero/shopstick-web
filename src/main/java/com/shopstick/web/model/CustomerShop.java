package com.shopstick.web.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class CustomerShop implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 312542631971985965L;
	private ShopUserModel user;
	private ItemModel item;
	private Integer itemIdToAdd;
	
	public CustomerShop() {
		this.item = new ItemModel();
	}
}