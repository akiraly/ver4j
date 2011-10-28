package com.github.ver4j;

import java.util.Locale;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;

public class TextVerifier<S extends CharSequence> extends
		AObjectVerifierAwareVerifier {
	private static final String FAILED_NOT_EMPTY_CAUSE = "it is an empty text";

	private static final String FAILED_NOT_BLANK_CAUSE = "it is an empty text or it contains only whitespace characters";

	private final ExceptionFactory<?> notEmptyExceptionFactory;

	private final ExceptionFactory<?> notBlankExceptionFactory;

	public TextVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull ExceptionTypeInfo<?> textExceptionTypeInfo) {
		super(objectVerifier);
		notEmptyExceptionFactory = exceptionFactoryOf(textExceptionTypeInfo,
				FAILED_NOT_EMPTY_CAUSE);
		notBlankExceptionFactory = exceptionFactoryOf(textExceptionTypeInfo,
				FAILED_NOT_BLANK_CAUSE);
	}

	@Nonnull
	public final <T extends S> T notEmpty(@Nonnull T text) {
		object().notNull(text);
		if (isDisabled() || text.length() > 0)
			return text;
		throw notEmptyExceptionFactory.newException();
	}

	@Nonnull
	public final <T extends S> T notEmpty(@Nonnull T text, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(text, errorMessage, errorMessageArgs);
		if (isDisabled() || text.length() > 0)
			return text;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	@Nonnull
	public final <T extends S> T notEmptyCm(@Nonnull T text,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(text, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || text.length() > 0)
			return text;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	@Nonnull
	public final <T extends S> T notBlank(@Nonnull T text) {
		if (isDisabled() || !StringUtils.isBlank(text))
			return text;
		throw notBlankExceptionFactory.newException();
	}

	@Nonnull
	public final <T extends S> T notBlank(@Nonnull T text, Object errorMessage,
			Object... errorMessageArgs) {
		if (isDisabled() || !StringUtils.isBlank(text))
			return text;
		throw notBlankExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	@Nonnull
	public final <T extends S> T notBlankCm(@Nonnull T text,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		if (isDisabled() || !StringUtils.isBlank(text))
			return text;
		throw notBlankExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}
}
