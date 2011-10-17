package com.github.ver4j;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

class TestVerificationException extends ContextedRuntimeException implements
		IVerificationException {
	private static final long serialVersionUID = 1L;

	private final String category;

	public TestVerificationException(String message, String category) {
		super(message);
		this.category = category;
	}

	@Override
	public final String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		if (category != null) {
			return '[' + category + ']' + super.toString();
		}

		return super.toString();
	}
}