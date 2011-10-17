package com.github.ver4j.state.exception;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

import com.github.ver4j.IVerificationException;

public class StateVerificationException extends ContextedRuntimeException
		implements IVerificationException {
	private static final long serialVersionUID = 1L;

	private final String category;

	public StateVerificationException(String message, String category) {
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
