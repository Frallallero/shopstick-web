package com.shopstick.web.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Shop implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 312542631971985965L;
	private ShopUserModel user;
	private ItemModel item;
	private Integer itemIdToAdd;
	
	public Shop() {
		this.item = new ItemModel();
	}
}