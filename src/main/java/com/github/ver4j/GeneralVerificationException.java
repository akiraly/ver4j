package com.github.ver4j;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class GeneralVerificationException extends ContextedRuntimeException implements
		ICategorized {
	private static final long serialVersionUID = 1L;

	private final String category;

	public GeneralVerificationException(String message, String category) {
		super(message);
		this.category = category;
		if (category != null) {
			addContextValue("verification-category", category);
		}
	}

	@Override
	public final String getCategory() {
		return category;
	}

	@Override
	public String toString() {
		if (category != null) {
			return '{' + category + "} " + super.toString();
		}

		return super.toString();
	}
}
