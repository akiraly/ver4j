package com.github.ver4j;

import java.util.Locale;

import javax.annotation.Nonnull;

public class ObjectVerifier extends AVerifier {
	private static final String FAILED_IS_TRUE_CAUSE = "it is false";

	private static final String FAILED_NOT_NULL_CAUSE = "it is null";

	private final String category;

	private final ExceptionMessageInfo defaultExceptionMessageInfo;

	private final ExceptionFactory<?> generalExceptionFactory;

	private final ExceptionFactory<?> nullExceptionFactory;

	public ObjectVerifier(String category,
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo) {
		super();
		this.category = category;
		this.defaultExceptionMessageInfo = defaultExceptionMessageInfo;
		generalExceptionFactory = ExceptionFactory.of(category,
				generalExceptionTypeInfo,
				defaultExceptionMessageInfo.appendCause(FAILED_IS_TRUE_CAUSE));
		nullExceptionFactory = ExceptionFactory.of(category,
				nullExceptionTypeInfo,
				defaultExceptionMessageInfo.appendCause(FAILED_NOT_NULL_CAUSE));
	}

	public final void isTrue(boolean expression) {
		if (isDisabled() || expression) {
			return;
		}
		throw generalExceptionFactory.newException();
	}

	public final void isTrue(boolean expression, Object errorMessage) {
		if (isDisabled() || expression) {
			return;
		}
		throw generalExceptionFactory.newException(errorMessage);
	}

	public final void isTrue(boolean expression, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		if (isDisabled() || expression) {
			return;
		}
		throw generalExceptionFactory.newException(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final <T> T notNull(@Nonnull T object) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw nullExceptionFactory.newException();
	}

	public final <T> T notNull(@Nonnull T object, Object errorMessage) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw nullExceptionFactory.newException(errorMessage);
	}

	public final <T> T notNull(@Nonnull T object, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw nullExceptionFactory.newException(errorMessageTemplate, locale,
				errorMessageArgs);
	}

	@Nonnull
	public ExceptionMessageInfo getDefaultExceptionMessageInfo() {
		return defaultExceptionMessageInfo;
	}

	@Override
	public final String getCategory() {
		return category;
	}
}
