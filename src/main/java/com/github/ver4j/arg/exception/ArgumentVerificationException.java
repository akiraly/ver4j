package com.github.ver4j.arg.exception;

import javax.annotation.Nonnull;

import com.github.ver4j.GeneralVerificationException;

public class ArgumentVerificationException extends GeneralVerificationException {
	private static final long serialVersionUID = 1L;

	public ArgumentVerificationException(String message, String category) {
		super(message, category);
	}

	public ArgumentVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
