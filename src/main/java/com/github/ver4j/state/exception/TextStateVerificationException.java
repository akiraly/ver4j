package com.github.ver4j.state.exception;

import javax.annotation.Nonnull;

public class TextStateVerificationException extends StateVerificationException {
	private static final long serialVersionUID = 1L;

	public TextStateVerificationException(String message, String category) {
		super(message, category);
	}

	public TextStateVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
