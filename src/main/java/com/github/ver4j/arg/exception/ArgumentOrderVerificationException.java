package com.github.ver4j.arg.exception;

import javax.annotation.Nonnull;

public class ArgumentOrderVerificationException extends
		ArgumentVerificationException {
	private static final long serialVersionUID = 1L;

	public ArgumentOrderVerificationException(String message, String category) {
		super(message, category);
	}

	public ArgumentOrderVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
