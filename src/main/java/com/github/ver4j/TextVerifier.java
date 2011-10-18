package com.github.ver4j;

import java.util.Locale;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.StringUtils;

public class TextVerifier extends AObjectVerifierAwareVerifier {
	private static final String FAILED_NOT_NULL_CAUSE = "it is an empty text";

	private static final String FAILED_NOT_BLANK_CAUSE = "it is an empty text or it contains only whitespace characters";

	private final ExceptionFactory<?> notEmptyExceptionFactory;

	private final ExceptionFactory<?> notBlankExceptionFactory;

	public TextVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull ExceptionTypeInfo<?> textExceptionTypeInfo) {
		super(objectVerifier);
		notEmptyExceptionFactory = ExceptionFactory.of(
				getCategory(),
				textExceptionTypeInfo,
				objectVerifier.getDefaultExceptionMessageInfo().appendCause(
						FAILED_NOT_NULL_CAUSE));
		notBlankExceptionFactory = ExceptionFactory.of(
				getCategory(),
				textExceptionTypeInfo,
				objectVerifier.getDefaultExceptionMessageInfo().appendCause(
						FAILED_NOT_BLANK_CAUSE));
	}

	public final void notEmpty(@Nonnull CharSequence text) {
		object().notNull(text);
		if (isDisabled() || text.length() > 0) {
			return;
		}
		throw notEmptyExceptionFactory.newException();
	}

	public final void notEmpty(@Nonnull CharSequence text, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(text, errorMessage, errorMessageArgs);
		if (isDisabled() || text.length() > 0) {
			return;
		}
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void notEmptyCm(@Nonnull CharSequence text,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(text, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || text.length() > 0) {
			return;
		}
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final void notBlank(@Nonnull CharSequence text) {
		if (isDisabled() || !StringUtils.isBlank(text)) {
			return;
		}
		throw notBlankExceptionFactory.newException();
	}

	public final void notBlank(@Nonnull CharSequence text, Object errorMessage,
			Object... errorMessageArgs) {
		if (isDisabled() || !StringUtils.isBlank(text)) {
			return;
		}
		throw notBlankExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void notBlankCm(@Nonnull CharSequence text,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		if (isDisabled() || !StringUtils.isBlank(text)) {
			return;
		}
		throw notBlankExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	@Nonnull
	public final String strippedNotNull(@Nonnull String text) {
		String stripped = StringUtils.stripToNull(text);
		if (isDisabled() || stripped != null) {
			return stripped;
		}
		throw notBlankExceptionFactory.newException();
	}

	@Nonnull
	public final String strippedNotNull(@Nonnull String text,
			Object errorMessage, Object... errorMessageArgs) {
		String stripped = StringUtils.stripToNull(text);
		if (isDisabled() || stripped != null) {
			return stripped;
		}
		throw notBlankExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	@Nonnull
	public final String strippedNotNullCm(@Nonnull String text,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		String stripped = StringUtils.stripToNull(text);
		if (isDisabled() || stripped != null) {
			return stripped;
		}
		throw notBlankExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}
}
