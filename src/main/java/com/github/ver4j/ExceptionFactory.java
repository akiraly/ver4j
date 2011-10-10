package com.github.ver4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.annotation.Nonnull;

public class ExceptionFactory {
	@Nonnull
	private final String defaultMessageTemplate;

	@Nonnull
	private final Locale defaultLocale;

	public ExceptionFactory(String type) {
		this(type + " (%s) failed verification because %s.", Locale.ENGLISH);
	}

	protected ExceptionFactory(@Nonnull String defaultMessageTemplate,
			@Nonnull Locale defaultLocale) {
		this.defaultMessageTemplate = defaultMessageTemplate;
		this.defaultLocale = defaultLocale;
	}

	public <T extends RuntimeException & IVerificationException> T createException(
			@Nonnull IVerifier verifier,
			@Nonnull ExceptionInfo<T> exceptionInfo, Locale locale,
			String messageTemplate, Object[] messageTemplateArgs) {
		String message = String.format(locale != null ? locale : defaultLocale,
				messageTemplate != null ? messageTemplate
						: defaultMessageTemplate, messageTemplateArgs);
		try {
			return exceptionInfo.create(message, verifier.getCategory());
		} catch (InstantiationException e) {
			throw failException(verifier, exceptionInfo, message, e);
		} catch (IllegalAccessException e) {
			throw failException(verifier, exceptionInfo, message, e);
		} catch (InvocationTargetException e) {
			throw failException(verifier, exceptionInfo, message, e);
		}
	}

	@Nonnull
	private IllegalStateException failException(@Nonnull IVerifier verifier,
			@Nonnull ExceptionInfo<?> exceptionInfo, String message,
			@Nonnull Exception cause) {
		return new IllegalStateException(
				String.format(
						defaultLocale,
						"Exception while building verification exception with type (%s) and message (%s) for verifier (%s).",
						exceptionInfo.getType(), message, verifier), cause);
	}

	@Nonnull
	public String getDefaultMessageTemplate() {
		return defaultMessageTemplate;
	}
}
