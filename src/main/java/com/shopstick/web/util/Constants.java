package com.shopstick.web.util;

public class Constants {

//	CONTROLLER MAPPINGS
	public static final String MAPPING_LOGIN_CONTROLLER = "/login";
	public static final String MAPPING_SHOP_CONTROLLER = "/shop";
	public static final String MAPPING_CUSTOMER_SHOP_CONTROLLER = "/customerShop";

//	FORMS
	public static final String LOGIN_FORM = "loginForm";
	public static final String SHOP_FORM = "shopForm";
	public static final String CUSTOMER_SHOP_FORM = "customerShopForm";
	
//	PAGES
	public static final String LOGIN_PAGE = "login";
	public static final String SHOP_PAGE = "shop";
	public static final String CUSTOMER_SHOP_PAGE = "customerShop";

	public static final String SHOP_BE_URL = "http://localhost:8081/core-rest";
	public static final String HTTP_STATUS_CODE_401 = "401";
	public static final String HTTP_STATUS_CODE_403 = "403";

//	RESOURCES
	public static final String USER_RESOURCE_URL = "/users";
	public static final String CREDENTIALS_RESOURCE_URL = "/users/credentials/{username}/{password}";
	public static final String ITEM_RESOURCE_URL = "/items";
	public static final String ADD_TO_CART_RESOURCE_URL = "/carts/add-item";
	
//	ROLES
	public static final Integer ROLE_ID_OWNER = 1;
	public static final Integer ROLE_ID_CUSTOMER = 2;
	
}
