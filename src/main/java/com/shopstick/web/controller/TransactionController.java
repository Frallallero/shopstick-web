package com.shopstick.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopstick.web.client.RestClient;
import com.shopstick.web.model.Transaction;
import com.shopstick.web.util.Constants;

@Controller
@RequestMapping(Constants.MAPPING_TRANSACTION_CONTROLLER)
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	private RestClient restClient = new RestClient();

	
	@GetMapping
	public String getHome (
			@ModelAttribute(Constants.TRANSACTION_FORM) Transaction transaction,
			Model model, Errors errors) throws Exception {

		logger.info("TransactionController :: getHome");
		return Constants.TRANSACTION_PAGE;
	}
	
	
	/**
	 * Invoke this method to purchase items in the cart
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "purchase")
	public String purchase(@ModelAttribute(Constants.TRANSACTION_FORM) Transaction transaction,
			Model model, Errors errors) throws Exception {

		logger.info("TransactionController :: purchase");
//		restClient.callRestServicePost(Constants.SHOP_BE_URL, Constants.ITEM_RESOURCE_URL, ItemModel.class, shop.setItemModel());
		
		return Constants.TRANSACTION_PAGE;
	}
	
}
