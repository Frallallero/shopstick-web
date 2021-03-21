package com.shopstick.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopstick.web.client.RestClient;
import com.shopstick.web.exception.GenericHttpException;
import com.shopstick.web.exception.UnauthorizedException;
import com.shopstick.web.model.CustomerShop;
import com.shopstick.web.model.ItemModel;
import com.shopstick.web.model.Transaction;
import com.shopstick.web.model.UserItem;
import com.shopstick.web.util.Constants;

@Controller
@RequestMapping(Constants.MAPPING_CUSTOMER_SHOP_CONTROLLER)
public class CustomerShopController {

	private static final Logger logger = LoggerFactory.getLogger(CustomerShopController.class);
	private RestClient restClient = new RestClient();

	public static final String REDIRECT = "redirect:";

	@ModelAttribute("categories")
    public List<String> getCategories() {
        return Constants.CATEGORIES;
    }
	
	@SuppressWarnings("unchecked")
	@ModelAttribute("itemsList")
    public List<ItemModel> availableItems() throws UnauthorizedException, GenericHttpException {
        return restClient.callRestServiceGet(Constants.SHOP_BE_URL, Constants.ITEM_RESOURCE_URL, List.class, new HashMap<>());
    }
	
	@GetMapping
	public String getHome (
			@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop,
			Model model, Errors errors) throws Exception {

		logger.info("CustomerShopController :: getHome");
		return Constants.CUSTOMER_SHOP_PAGE;
	}
	
	/**
	 * Invoke this method to add item to cart
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "addToCart")
	public String addToCart(@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop,
			Model model, Errors errors) throws Exception {

		logger.info("CustomerShopController :: addToCart");
		Map<String, Integer> postParams = new HashMap<>();
		postParams.put("userId", customerShop.getUserId());
		postParams.put("itemId", 1);
		postParams.put("quantity", 1);
		restClient.callRestServicePost(Constants.SHOP_BE_URL, Constants.ADD_TO_CART_RESOURCE_URL, ItemModel.class, postParams);
		
		retrieveCustomerCart(customerShop, model, errors);
		return Constants.CUSTOMER_SHOP_PAGE;
	}
	
	/**
	 * Invoke this method to retrieve customer cart items
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@PostMapping(params = "retrieveCustomerCart")
	public String retrieveCustomerCart(@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop,
			Model model, Errors errors) throws Exception {

		logger.info("CustomerShopController :: retrieveCustomerCart");
		Map<String, Integer> getParams = new HashMap<>();
		getParams.put("id", customerShop.getUserId());
		List<UserItem> cartItems = restClient.callRestServiceGet(Constants.SHOP_BE_URL, Constants.USER_CART_ITEMS_RESOURCE_URL, List.class, getParams);
		model.addAttribute("cartItems", cartItems);
		return Constants.CUSTOMER_SHOP_PAGE;
	}
	
	
	/**
	 * Invoke this method to checkout
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "checkout")
	public String checkout(@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop,
			Model model, RedirectAttributes redirect, Errors errors) {

		logger.info("CusomerShopController :: checkout");

		Transaction transaction = new Transaction();
		transaction.setCartId(1);
		redirect.addFlashAttribute(Constants.TRANSACTION_FORM, transaction);
		return REDIRECT + Constants.TRANSACTION_PAGE;
	}
	
}
