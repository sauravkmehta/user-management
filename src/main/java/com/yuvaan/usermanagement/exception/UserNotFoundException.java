package com.yuvaan.usermanagement.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 2192614744051688584L;
	
	public UserNotFoundException() {
		super();
	}
	
	public UserNotFoundException(final Integer userId) {
		super(String.format("User with userId %d not found", userId));
	}
	
	public UserNotFoundException(final String userLastName) {
		super(String.format("User with lastName %s not found", userLastName));
	}

}
