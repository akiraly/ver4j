package com.github.ver4j;

class BrokenTestVerificationException extends GeneralVerificationException {
	private static final long serialVersionUID = 1L;

	public BrokenTestVerificationException(String message) {
		super(message, null);
	}
}