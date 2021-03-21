package com.shopstick.web.validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import org.thymeleaf.util.StringUtils;

import com.shopstick.web.model.Login;

@Component
public class LoginValidator implements Validator {

	protected final Logger logger = LoggerFactory.getLogger(this.getClass());
	public static final String USERNAME = "username";
	public static final String PASSWORD = "password";

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@Override
	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
	}

	public void validateFields(Login login, Errors errors) {
		if(StringUtils.isEmptyOrWhitespace(login.getUsername())) {
			errors.rejectValue("error", "validation.emptyField", new Object[] { USERNAME }, "");
		}
		if(StringUtils.isEmptyOrWhitespace(login.getPassword())) {
			errors.rejectValue("error", "validation.emptyField", new Object[] { PASSWORD }, "");
		}
	}
}
