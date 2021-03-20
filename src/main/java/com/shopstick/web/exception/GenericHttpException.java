package com.shopstick.web.exception;

public class GenericHttpException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public GenericHttpException() {
	}

	public GenericHttpException(String message) {
		super(message);
	}

	public GenericHttpException(Throwable cause) {
		super(cause);
	}

	public GenericHttpException(String message, Throwable cause) {
		super(message, cause);
	}

	public GenericHttpException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
