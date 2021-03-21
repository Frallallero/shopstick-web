package com.shopstick.web.controller;

import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.shopstick.web.model.ItemModel;
import com.shopstick.web.model.Shop;
import com.shopstick.web.util.Constants;
import com.shopstick.web.validator.ShopValidator;

@Controller
@RequestMapping(Constants.MAPPING_SHOP_CONTROLLER)
public class ShopController {

	private static final Logger logger = LoggerFactory.getLogger(ShopController.class);
	private RestClient restClient = new RestClient();

	public static final String REDIRECT = "redirect:";

	@Autowired
	private ShopValidator shopValidator;
	
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
			@ModelAttribute(Constants.SHOP_FORM) Shop shop,
			Model model, Errors errors) throws Exception {

		logger.info("ShopController :: getHome");
		return Constants.SHOP_PAGE;
	}
	
	/**
	 * Invoke this method to clear the page
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "clear")
	public String clear(@ModelAttribute(Constants.SHOP_FORM) Shop shop,
			Model model, Errors errors) throws Exception {

		logger.info("ShopController :: clear");
		model.addAttribute(Constants.SHOP_FORM, new Shop());
		return Constants.SHOP_PAGE;
	}
	
	/**
	 * Invoke this method to create a new Item
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "createItem")
	public String createItem(@ModelAttribute(Constants.SHOP_FORM) Shop shop,
			Model model, RedirectAttributes redirect, Errors errors) throws Exception {

		logger.info("ShopController :: createItem");
		shopValidator.validateFields(shop, errors);
		
		if(!errors.hasErrors()) {
			restClient.callRestServicePost(Constants.SHOP_BE_URL, Constants.ITEM_RESOURCE_URL, ItemModel.class, shop.setItemModel());
			return REDIRECT + Constants.SHOP_PAGE;
		}
		
		return Constants.SHOP_PAGE;
	}
	
}
