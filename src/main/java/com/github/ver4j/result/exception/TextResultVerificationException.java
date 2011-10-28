package com.github.ver4j.result.exception;

import javax.annotation.Nonnull;

public class TextResultVerificationException extends
		ResultVerificationException {
	private static final long serialVersionUID = 1L;

	public TextResultVerificationException(String message, String category) {
		super(message, category);
	}

	public TextResultVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
