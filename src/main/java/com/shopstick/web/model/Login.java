package com.shopstick.web.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class Login implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 312542631971985965L;
	private String error;
	private String username;
	private String password;
}