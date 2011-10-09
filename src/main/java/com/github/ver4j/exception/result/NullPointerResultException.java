package com.github.ver4j.exception.result;

public class NullPointerResultException extends ResultVerificationException {
	private static final long serialVersionUID = 1L;

	public NullPointerResultException(String message, String category) {
		super(message, category);
	}
}
