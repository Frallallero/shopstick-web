package com.shopstick.web.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.shopstick.web.model.Transaction;

@Component
public class TransactionValidator implements Validator {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String CARD_NUMBER = "card number";
	public static final String CVV = "cvv";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

	public void validateFields(Transaction transaction, Errors errors) {
		if(StringUtils.isEmptyOrWhitespace(transaction.getCardNumber())) {
			errors.rejectValue("error", "validation.emptyField", new Object[] { CARD_NUMBER }, "");
		}
		if(StringUtils.isEmptyOrWhitespace(transaction.getCvv())) {
			errors.rejectValue("error", "validation.emptyField", new Object[] { CVV }, "");
		}
	}
}
