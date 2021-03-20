package com.shopstick.web.model;

import lombok.Data;

@Data
public class ItemModel {
	private Integer id;
	private String name;
	private String description;
	private String category;
	private String price;
	private String image;
	private Integer stockNumber;
}
