package com.github.ver4j;

import javax.annotation.Nonnull;

class TestVerificationException extends GeneralVerificationException {
	private static final long serialVersionUID = 1L;

	public TestVerificationException(String message, String category) {
		super(message, category);
	}

	public TestVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}