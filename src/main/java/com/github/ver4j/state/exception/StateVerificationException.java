package com.github.ver4j.state.exception;

import javax.annotation.Nonnull;

import com.github.ver4j.GeneralVerificationException;

public class StateVerificationException extends GeneralVerificationException {
	private static final long serialVersionUID = 1L;

	public StateVerificationException(String message, String category) {
		super(message, category);
	}

	public StateVerificationException(String message, String category,
			@Nonnull Throwable cause) {
		super(message, category, cause);
	}
}
