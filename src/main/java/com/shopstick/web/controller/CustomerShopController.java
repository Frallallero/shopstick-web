
package com.shopstick.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.shopstick.web.client.RestClient;
import com.shopstick.web.exception.GenericHttpException;
import com.shopstick.web.exception.UnauthorizedException;
import com.shopstick.web.model.CustomerShop;
import com.shopstick.web.model.ItemModel;
import com.shopstick.web.model.Login;
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
	
	@ModelAttribute("cartItems")
    public UserItem[] retrieveCart(@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop) throws Exception {
		return retrieveCustomerCart(customerShop);
    }
	
	@SuppressWarnings("unchecked")
	@ModelAttribute("itemsList")
    public List<ItemModel> availableItems(@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop) throws UnauthorizedException, GenericHttpException {
        Map<String, String> getParams = new HashMap<>();;
		if(!ObjectUtils.isEmpty(customerShop.getCategory()) && !customerShop.getCategory().equalsIgnoreCase("ALL")) {
			getParams.put("categoryName", customerShop.getCategory());
			return restClient.callRestServiceGet(Constants.SHOP_BE_URL, Constants.ITEM_CATEGORY_RESOURCE_URL, List.class, getParams);
        }
		return restClient.callRestServiceGet(Constants.SHOP_BE_URL, Constants.ITEM_RESOURCE_URL, List.class, getParams);
    }
	
	@GetMapping
	public String getHome (
			@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop,
			Model model, Errors errors) throws Exception {

		logger.info("CustomerShopController :: getHome");
		return Constants.CUSTOMER_SHOP_PAGE;
	}
	
	@PostMapping(params = "reload")
	public String reload(Model model,
			@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop) {

		model.addAttribute(Constants.CUSTOMER_SHOP_FORM, customerShop);
		return Constants.CUSTOMER_SHOP_PAGE;
	}
	
	/**
	 * Invoke this method to add item to cart
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "addToCart")
	public String addToCart(
			@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop,
			@RequestParam(value = "addToCart", required = true) String addToCart,
			Model model, RedirectAttributes redirect) throws Exception {

		logger.info("CustomerShopController :: addToCart");
		String itemId = addToCart.replace("addToCart@", "");
		Map<String, Integer> postParams = new HashMap<>();
		postParams.put("userId", customerShop.getUserId());
		postParams.put("itemId", Integer.valueOf(itemId));
		postParams.put("quantity", customerShop.getItemToAddQuantity());
		restClient.callRestServicePost(Constants.SHOP_BE_URL, Constants.ADD_TO_CART_RESOURCE_URL, ItemModel.class, postParams);
		
		redirect.addFlashAttribute(Constants.CUSTOMER_SHOP_FORM, customerShop);
		return REDIRECT + Constants.CUSTOMER_SHOP_PAGE;
	}
	
	/**
	 * Invoke this method to add item to cart
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "deleteFromCart")
	public String deleteFromCart(
			@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop,
			@RequestParam(value = "deleteFromCart", required = true) String deleteFromCart,
			Model model, RedirectAttributes redirect) throws Exception {

		logger.info("CustomerShopController :: deleteFromCart");
		String itemId = deleteFromCart.replace("deleteFromCart@", "");
		Map<String, String> postParams = new HashMap<>();
		postParams.put("cartId", String.valueOf(customerShop.getCartId()));
		postParams.put("itemId", String.valueOf(Integer.valueOf(itemId)));
		restClient.callRestServicePost(Constants.SHOP_BE_URL, Constants.DELETE_FROM_CART_RESOURCE_URL, boolean.class, postParams);
		redirect.addFlashAttribute(Constants.CUSTOMER_SHOP_FORM, customerShop);
		return REDIRECT + Constants.CUSTOMER_SHOP_PAGE;
	}
	
	private UserItem[] retrieveCustomerCart(@ModelAttribute(Constants.CUSTOMER_SHOP_FORM) CustomerShop customerShop) throws Exception {

		logger.info("CustomerShopController :: retrieveCustomerCart");
		Map<String, Integer> getParams = new HashMap<>();
		getParams.put("id", customerShop.getUserId());
		UserItem[] cartItems = restClient.callRestServiceGet(Constants.SHOP_BE_URL, Constants.USER_CART_ITEMS_RESOURCE_URL, UserItem[].class, getParams);
		if(cartItems.length>0) {
			customerShop.setCartId(cartItems[0].getCartId());
			for(UserItem userItem : cartItems) {
				customerShop.setTotalAmount(customerShop.getTotalAmount().add(userItem.getTotalAmount()));
			}
		}
		return cartItems;
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
		transaction.setCartId(customerShop.getCartId());
		redirect.addFlashAttribute(Constants.TRANSACTION_FORM, transaction);
		return REDIRECT + Constants.TRANSACTION_PAGE;
	}
	
	/**
	 * Invoke this method to go back to login page
	 * 
	 * @return
	 */
	@PostMapping(params = "back")
	public String back(RedirectAttributes redirect) {
		logger.info("LoginController :: login");
		redirect.addFlashAttribute(Constants.LOGIN_FORM, new Login());
		return REDIRECT + Constants.LOGIN_PAGE;
	}
	
}
