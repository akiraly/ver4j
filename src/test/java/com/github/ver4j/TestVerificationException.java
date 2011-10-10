package com.github.ver4j;

class TestVerificationException extends RuntimeException implements
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