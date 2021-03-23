package com.shopstick.web.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.shopstick.web.model.Shop;

@Component
public class ShopValidator implements Validator {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String ITEM_NAME = "item name";
	public static final String ITEM_STOCK = "item stock";
	public static final String ITEM_PRICE = "item unit price";
	public static final String ITEM_CATEGORY = "item category";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

	public void validateFields(Shop shop, Errors errors) {
		if(StringUtils.isEmptyOrWhitespace(shop.getItemName())) {
			errors.rejectValue("error", "validation.emptyField", new Object[] { ITEM_NAME }, "");
		}
		if(shop.getItemStockNumber()==null) {
			errors.rejectValue("error", "validation.emptyField", new Object[] { ITEM_STOCK }, "");
		} else if(shop.getItemStockNumber()<=0) {
			errors.rejectValue("error", "validation.invalidStock", new Object[] { "" }, "");
		}
		if(StringUtils.isEmptyOrWhitespace(shop.getItemPrice())) {
			errors.rejectValue("error", "validation.emptyField", new Object[] { ITEM_PRICE }, "");
		}
		if(StringUtils.isEmptyOrWhitespace(shop.getItemCategory())) {
			errors.rejectValue("error", "validation.emptyField", new Object[] { ITEM_CATEGORY }, "");
		}
	}
}
