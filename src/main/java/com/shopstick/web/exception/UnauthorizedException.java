package com.shopstick.web.exception;

public class UnauthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedException() {
	}

	public UnauthorizedException(String arg0) {
		super(arg0);
	}

	public UnauthorizedException(Throwable arg0) {
		super(arg0);
	}

	public UnauthorizedException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public UnauthorizedException(String arg0, Throwable arg1, boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
