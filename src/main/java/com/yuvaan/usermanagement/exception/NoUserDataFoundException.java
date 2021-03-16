package com.yuvaan.usermanagement.exception;

public class NoUserDataFoundException extends RuntimeException {

	private static final long serialVersionUID = 4079414252076734529L;

	public NoUserDataFoundException() {
		super("No data found for user.");
	}

}
