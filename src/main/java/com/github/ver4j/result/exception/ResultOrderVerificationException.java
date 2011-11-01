package com.github.ver4j.result.exception;

import javax.annotation.Nonnull;

public class ResultOrderVerificationException extends
		ResultVerificationException {
	private static final long serialVersionUID = 1L;

	public ResultOrderVerificationException(String message, String category) {
		super(message, category);
	}

	public ResultOrderVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
