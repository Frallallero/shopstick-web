package com.shopstick.web.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

@Component
public class ShopValidator implements Validator {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

	public void validateEmptyField(String field, Errors errors) {
		if (StringUtils.isEmptyOrWhitespace(field)) {
			errors.rejectValue("error", "validation.itemNotSelected", new Object[] { "" }, "");
		}
	}
}
