package com.github.ver4j.arg.exception;

import javax.annotation.Nonnull;

public class NullPointerArgumentVerificationException extends
		ArgumentVerificationException {
	private static final long serialVersionUID = 1L;

	public NullPointerArgumentVerificationException(String message,
			String category) {
		super(message, category);
	}

	public NullPointerArgumentVerificationException(String message,
			String category, @Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
