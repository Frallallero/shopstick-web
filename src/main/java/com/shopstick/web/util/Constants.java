package com.shopstick.web.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Constants {

//	CONTROLLER MAPPINGS
	public static final String MAPPING_LOGIN_CONTROLLER = "/login";
	public static final String MAPPING_SHOP_CONTROLLER = "/shop";
	public static final String MAPPING_CUSTOMER_SHOP_CONTROLLER = "/customerShop";
	public static final String MAPPING_TRANSACTION_CONTROLLER = "/transaction";

//	FORMS
	public static final String LOGIN_FORM = "loginForm";
	public static final String SHOP_FORM = "shopForm";
	public static final String CUSTOMER_SHOP_FORM = "customerShopForm";
	public static final String TRANSACTION_FORM = "transactionForm";
	
//	PAGES
	public static final String LOGIN_PAGE = "login";
	public static final String SHOP_PAGE = "shop";
	public static final String CUSTOMER_SHOP_PAGE = "customerShop";
	public static final String TRANSACTION_PAGE = "transaction";

//	URL
	public static final String SHOP_BE_URL = "http://localhost:8081/core-rest";

//	RESOURCES
	public static final String USER_RESOURCE_URL = "/users";
	public static final String CREDENTIALS_RESOURCE_URL = "/users/credentials/{username}/{password}";
	public static final String ITEM_RESOURCE_URL = "/items";
	public static final String ITEM_CATEGORY_RESOURCE_URL = "/items/categories/{categoryName}";
	public static final String ADD_TO_CART_RESOURCE_URL = "/carts/add-item";
	public static final String DELETE_FROM_CART_RESOURCE_URL = "/carts/delete-item";
	public static final String USER_CART_ITEMS_RESOURCE_URL = "/users/{id}/cart-items";
	public static final String PURCHASE_RESOURCE_URL = "/transactions/confirm";
	
	
//	ROLES
	public static final Integer ROLE_ID_OWNER = 1;
	public static final Integer ROLE_ID_CUSTOMER = 2;
	
	public static final String HTTP_STATUS_CODE_401 = "401";
	public static final String HTTP_STATUS_CODE_403 = "403";
	public static final List<String> CATEGORIES = new ArrayList<>(Arrays.asList("FORK", "SPOON", "CHOPSTICK"));

	
}
