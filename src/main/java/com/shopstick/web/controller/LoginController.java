package com.shopstick.web.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

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
import com.shopstick.web.model.Shop;
import com.shopstick.web.model.ShopUserModel;
import com.shopstick.web.util.Constants;
import com.shopstick.web.validator.LoginValidator;

@Controller
@RequestMapping(Constants.MAPPING_LOGIN_CONTROLLER)
public class LoginController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	public static final String REDIRECT = "redirect:";

	private RestClient restClient = new RestClient();
	
	@Autowired
	private LoginValidator loginValidator;

	
	@GetMapping
	public String getHome(@ModelAttribute(Constants.LOGIN_FORM) Login login, Model model, Errors errors) throws Exception {
		logger.info("LoginController :: getHome");
		return Constants.LOGIN_PAGE;
	}
	
	/**
	 * Invoke this method to check login
	 * 
	 * @return
	 * @throws Exception
	 */
	@PostMapping(params = "login")
	public String login(@ModelAttribute(Constants.LOGIN_FORM) Login login,
			Model model, RedirectAttributes redirect, Errors errors) throws Exception {

		logger.info("LoginController :: login");
		loginValidator.validateFields(login, errors);
		
		if(!errors.hasErrors()) {
			ShopUserModel user = retrieveUser(login, model, errors);

			if(user!=null) {
				if(user.getRole().getId().equals(Constants.ROLE_ID_OWNER)) {
					Shop shop = new Shop();
					shop.setUserId(user.getId());
					shop.setUserName(user.getName());
					redirect.addFlashAttribute(Constants.SHOP_FORM, shop);
					return REDIRECT + Constants.SHOP_PAGE;
				} else {
					CustomerShop customerShop = new CustomerShop();
					customerShop.setUser(user);
					redirect.addFlashAttribute(Constants.SHOP_FORM, customerShop);
					return REDIRECT + Constants.SHOP_PAGE;
				}
			}
		}
		return Constants.LOGIN_PAGE;
	}
	
	private ShopUserModel retrieveUser(Login login, Model model, Errors errors) throws Exception {

		logger.info("LoginController :: retrieveUser");
		Map<String, String> getParams = new HashMap<>();
		getParams.put("username", login.getUsername());
		getParams.put("password", login.getPassword());
		return restClient.callRestServiceGet(Constants.SHOP_BE_URL, Constants.CREDENTIALS_RESOURCE_URL, ShopUserModel.class, getParams);
	}
	
}
