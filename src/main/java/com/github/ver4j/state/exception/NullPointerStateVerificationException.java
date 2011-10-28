package com.github.ver4j.state.exception;

import javax.annotation.Nonnull;

public class NullPointerStateVerificationException extends
		StateVerificationException {
	private static final long serialVersionUID = 1L;

	public NullPointerStateVerificationException(String message, String category) {
		super(message, category);
	}

	public NullPointerStateVerificationException(String message,
			String category, @Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
