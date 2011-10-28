package com.github.ver4j.result.exception;

import javax.annotation.Nonnull;

import com.github.ver4j.GeneralVerificationException;

public class ResultVerificationException extends GeneralVerificationException {
	private static final long serialVersionUID = 1L;

	public ResultVerificationException(String message, String category) {
		super(message, category);
	}

	public ResultVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
