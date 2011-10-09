package com.github.ver4j;

import java.util.Locale;

public class ObjectVerifier extends AVerifier {
	private final String category;

	private final ExceptionFactory<?> generalExceptionFactory;

	private final ExceptionFactory<?> nullExceptionFactory;

	public ObjectVerifier(String category,
			ExceptionFactory<?> generalExceptionFactory,
			ExceptionFactory<?> nullExceptionFactory) {
		super();
		this.category = category;
		this.generalExceptionFactory = generalExceptionFactory;
		this.nullExceptionFactory = nullExceptionFactory;
	}

	public final void isTrue(boolean expression) {
		if (!isEnabled()) {
			return;
		}
		verifyTrue(expression, generalExceptionFactory);
	}

	public final void isTrue(boolean expression, Object errorMessage) {
		if (!isEnabled()) {
			return;
		}
		verifyTrue(expression, generalExceptionFactory, errorMessage);
	}

	public final void isTrue(boolean expression, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		if (!isEnabled()) {
			return;
		}
		verifyTrue(expression, generalExceptionFactory, errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final <T> T notNull(T object) {
		if (!isEnabled()) {
			return object;
		}
		verifyTrue(object != null, nullExceptionFactory);
		return object;
	}

	public final <T> T notNull(T object, Object errorMessage) {
		if (!isEnabled()) {
			return object;
		}
		verifyTrue(object != null, nullExceptionFactory, errorMessage);
		return object;
	}

	public final <T> T notNull(T object, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		if (!isEnabled()) {
			return object;
		}
		verifyTrue(object != null, nullExceptionFactory, errorMessageTemplate,
				locale, errorMessageArgs);
		return object;
	}

	protected ExceptionFactory<?> getGeneralExceptionFactory() {
		return generalExceptionFactory;
	}

	protected ExceptionFactory<?> getNullExceptionFactory() {
		return nullExceptionFactory;
	}

	@Override
	public final String getCategory() {
		return category;
	}
}
