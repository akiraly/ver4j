package com.github.ver4j.exception.arg;

import com.github.ver4j.IVerificationException;

public class ArgumentVerificationException extends IllegalArgumentException
		implements IVerificationException {
	private static final long serialVersionUID = 1L;

	private final String category;

	public ArgumentVerificationException(String message, String category) {
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
