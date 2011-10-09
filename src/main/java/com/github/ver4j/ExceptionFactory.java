package com.github.ver4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.annotation.Nonnull;

public class ExceptionFactory<T extends RuntimeException & IVerificationException> {
	@Nonnull
	private final String defaultMessageTemplate;

	@Nonnull
	private final Locale defaultLocale = Locale.ENGLISH;

	@Nonnull
	private final Class<T> exceptionType;

	@Nonnull
	private final Constructor<T> exceptionConstructor;

	public ExceptionFactory(@Nonnull Class<T> exceptionType,
			@Nonnull String defaultMessageTemplate) {
		this.exceptionType = exceptionType;
		this.defaultMessageTemplate = defaultMessageTemplate;

		try {
			this.exceptionConstructor = exceptionType.getConstructor(
					String.class, String.class);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(
					"No constructor with correct signature found for exception type: "
							+ exceptionType);
		}
	}

	@Nonnull
	public static <T extends RuntimeException & IVerificationException> ExceptionFactory<T> of(
			@Nonnull Class<T> exceptionType,
			@Nonnull String defaultMessageTemplate) {
		return new ExceptionFactory<T>(exceptionType, defaultMessageTemplate);
	}

	public void throwException(@Nonnull IVerifier verifier, Locale locale,
			String messageTemplate, Object[] messageTemplateArgs) {
		String message = String.format(locale != null ? locale : defaultLocale,
				messageTemplate != null ? messageTemplate
						: defaultMessageTemplate, messageTemplateArgs);
		RuntimeException re;
		try {
			re = exceptionConstructor.newInstance(message,
					verifier.getCategory());
		} catch (InstantiationException e) {
			re = failException(verifier, message, e);
		} catch (IllegalAccessException e) {
			re = failException(verifier, message, e);
		} catch (InvocationTargetException e) {
			re = failException(verifier, message, e);
		}

		throw re;
	}

	@Nonnull
	private IllegalStateException failException(@Nonnull IVerifier verifier,
			String message, @Nonnull Exception cause) {
		return new IllegalStateException(
				String.format(
						defaultLocale,
						"Exception while building verification exception with type (%s) and message (%s) for verifier (%s).",
						exceptionType, message, verifier), cause);
	}

	@Nonnull
	public String getDefaultMessageTemplate() {
		return defaultMessageTemplate;
	}

	@Nonnull
	public Class<T> getExceptionType() {
		return exceptionType;
	}
}
