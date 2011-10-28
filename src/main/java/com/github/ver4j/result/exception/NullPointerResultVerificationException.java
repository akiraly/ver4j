package com.github.ver4j.result.exception;

import javax.annotation.Nonnull;

public class NullPointerResultVerificationException extends
		ResultVerificationException {
	private static final long serialVersionUID = 1L;

	public NullPointerResultVerificationException(String message,
			String category) {
		super(message, category);
	}

	public NullPointerResultVerificationException(String message,
			String category, @Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
