package com.github.ver4j;

import javax.annotation.Nonnull;

class BrokenTestVerificationException extends GeneralVerificationException {
	private static final long serialVersionUID = 1L;

	public BrokenTestVerificationException(String message) {
		super(message, null);
	}

	public BrokenTestVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}