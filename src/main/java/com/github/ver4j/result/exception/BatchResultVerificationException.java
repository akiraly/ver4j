package com.github.ver4j.result.exception;

import javax.annotation.Nonnull;

public class BatchResultVerificationException extends
		ResultVerificationException {
	private static final long serialVersionUID = 1L;

	public BatchResultVerificationException(String message, String category) {
		super(message, category);
	}

	public BatchResultVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
