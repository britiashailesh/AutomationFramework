package com.aom.support;

public class UserException extends Exception {
	private static final long serialVersionUID = 1L;

	public UserException(String msg) {
		super(msg);
	}

	@Override
	public Throwable fillInStackTrace() {
		return this;
	}
}
