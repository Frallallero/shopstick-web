package com.shopstick.web.model;

import lombok.Data;

@Data
public class ShopUserModel {
	
	private Integer id;
	private RoleModel role;
	private String name;
	private String username;
	private String password;
//	private List<Transaction> transactions;
}