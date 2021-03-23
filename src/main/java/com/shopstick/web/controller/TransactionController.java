package com.shopstick.web.controller;

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
import com.shopstick.web.model.CustomerShop;
import com.shopstick.web.model.Login;
import com.shopstick.web.model.Transaction;
import com.shopstick.web.util.Constants;
import com.shopstick.web.validator.TransactionValidator;

@Controller
@RequestMapping(Constants.MAPPING_TRANSACTION_CONTROLLER)
public class TransactionController {

	private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);
	private RestClient restClient = new RestClient();
	
	@Autowired
	private TransactionValidator transactionValidator;

	public static final String REDIRECT = "redirect:";
	
	@GetMapping
	public String getHome (
			@ModelAttribute(Constants.TRANSACTION_FORM) Transaction transaction,
			Model model, Errors errors) {

		logger.info("TransactionController :: getHome");
		return Constants.TRANSACTION_PAGE;
	}
	
	
	/**
	 * Invoke this method to purchase items
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "purchase")
	public String purchase(@ModelAttribute(Constants.TRANSACTION_FORM) Transaction transaction,
			Model model, Errors errors) throws Exception {

		logger.info("TransactionController :: purchase");
		transactionValidator.validateFields(transaction, errors);
		
		String uuid = restClient.callRestServicePost(Constants.SHOP_BE_URL, Constants.PURCHASE_RESOURCE_URL, String.class, transaction);
		
		if(uuid!=null && !uuid.isEmpty()) {
			model.addAttribute("infoSuccess", true);
			model.addAttribute("uuid", uuid);
			Transaction tra = new Transaction();
			tra.setUserId(transaction.getUserId());
			tra.setUserName(transaction.getUserName());
			tra.setCartId(transaction.getCartId());
			model.addAttribute(Constants.TRANSACTION_FORM, tra);
		}
		return Constants.TRANSACTION_PAGE;
	}
	
	/**
	 * Invoke this method to go back to login page
	 * @param redirect
	 * @return
	 */
	@PostMapping(params = "back")
	public String back(RedirectAttributes redirect) {
		logger.info("TransactionController :: login");
		redirect.addFlashAttribute(Constants.LOGIN_FORM, new Login());
		return REDIRECT + Constants.LOGIN_PAGE;
	}
	
	/**
	 * Invoke this method to go back to cart page
	 * @param transaction
	 * @param redirect
	 * @return
	 */
	@PostMapping(params = "backToCart")
	public String backToCart(@ModelAttribute(Constants.TRANSACTION_FORM) Transaction transaction,
			RedirectAttributes redirect) {

		logger.info("TransactionController :: backToCart");

		CustomerShop customerShop = new CustomerShop();
		customerShop.setCartId(transaction.getCartId());
		customerShop.setUserId(transaction.getUserId());
		customerShop.setUserName(transaction.getUserName());
		redirect.addFlashAttribute(Constants.CUSTOMER_SHOP_FORM, customerShop);
		return REDIRECT + Constants.CUSTOMER_SHOP_PAGE;
	}
	
}
