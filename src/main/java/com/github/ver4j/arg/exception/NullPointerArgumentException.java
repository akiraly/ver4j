package com.github.ver4j.arg.exception;

public class NullPointerArgumentException extends ArgumentVerificationException {
	private static final long serialVersionUID = 1L;

	public NullPointerArgumentException(String message, String category) {
		super(message, category);
	}
}
