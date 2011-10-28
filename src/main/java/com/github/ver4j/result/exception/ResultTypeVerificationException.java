package com.github.ver4j.result.exception;

import javax.annotation.Nonnull;

public class ResultTypeVerificationException extends
		ResultVerificationException {
	private static final long serialVersionUID = 1L;

	public ResultTypeVerificationException(String message, String category) {
		super(message, category);
	}

	public ResultTypeVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
