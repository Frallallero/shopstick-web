package com.shopstick.web.model;

import lombok.Data;

@Data
public class UserItem {
	private Integer cartId;
	private ItemModel item;
	private Integer quantity;
}
