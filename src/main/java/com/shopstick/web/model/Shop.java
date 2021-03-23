package com.shopstick.web.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Shop implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 312542631971985965L;
	private Integer userId;
	private String userName;
	private Integer itemId;
	private String itemName;
	private String itemDescription;
	private String itemCategory;
	private String itemPrice;
	private String itemImage;
	private Integer itemStockNumber;
	private String error;

	private Integer itemIdToAdd;
	
	
	public ItemModel setItemModel() {
		ItemModel itemModel = new ItemModel();
		itemModel.setCategory(itemCategory);
		itemModel.setDescription(itemDescription);
//		TODO manage image
		itemModel.setImage(null);
		itemModel.setName(itemName);
		itemModel.setPrice(itemPrice);
		itemModel.setStockNumber(itemStockNumber);
		
		return itemModel;
	}
}