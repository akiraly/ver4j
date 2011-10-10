package com.github.ver4j;

import java.util.Locale;

import javax.annotation.Nonnull;

public class ObjectVerifier extends AVerifier {
	private final String category;

	/**
	 * Consider this:
	 * 
	 * ${type} (%s) failed verification because {cause}.
	 * 
	 * arg/state/return (myVar) failed verification because it is null.
	 * 
	 * This could reduce our need for one exception factory per type (composite
	 * verifier).
	 */
	private final ExceptionFactory exceptionFactory;

	private final String failedIsTrueCause = "it is false";

	private final String failedNotNullCause = "it is null";

	private final ExceptionInfo<?> generalExceptionInfo;

	private final ExceptionInfo<?> nullExceptionInfo;

	public ObjectVerifier(String category,
			@Nonnull ExceptionFactory exceptionFactory,
			@Nonnull ExceptionInfo<?> generalExceptionInfo,
			@Nonnull ExceptionInfo<?> nullExceptionInfo) {
		super();
		this.category = category;
		this.exceptionFactory = exceptionFactory;
		this.generalExceptionInfo = generalExceptionInfo;
		this.nullExceptionInfo = nullExceptionInfo;
	}

	public final void isTrue(boolean expression) {
		if (isDisabled() || expression) {
			return;
		}
		fail(generalExceptionInfo, failedIsTrueCause);
	}

	public final void isTrue(boolean expression, Object errorMessage) {
		if (isDisabled() || expression) {
			return;
		}
		fail(generalExceptionInfo, failedIsTrueCause, errorMessage);
	}

	public final void isTrue(boolean expression, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		if (isDisabled() || expression) {
			return;
		}
		fail(generalExceptionInfo, errorMessageTemplate, locale,
				errorMessageArgs);
	}

	public final <T> T notNull(@Nonnull T object) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw exception(nullExceptionInfo, failedNotNullCause);
	}

	public final <T> T notNull(@Nonnull T object, Object errorMessage) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw exception(nullExceptionInfo, failedNotNullCause, errorMessage);
	}

	public final <T> T notNull(@Nonnull T object, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw exception(nullExceptionInfo, errorMessageTemplate, locale,
				errorMessageArgs);
	}

	@Override
	@Nonnull
	public final ExceptionFactory getExceptionFactory() {
		return exceptionFactory;
	}

	@Override
	public final String getCategory() {
		return category;
	}
}
