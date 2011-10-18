package com.github.ver4j;

class BrokenTestVerificationException2 extends GeneralVerificationException {
	private static final long serialVersionUID = 1L;

	public BrokenTestVerificationException2(String message, String category) {
		super(message, category);
		throw new UnsupportedOperationException("You shouldn't use me.");
	}
}