package com.github.ver4j.exception.state;

public class NullPointerStateException extends StateVerificationException {
	private static final long serialVersionUID = 1L;

	public NullPointerStateException(String message, String category) {
		super(message, category);
	}
}
