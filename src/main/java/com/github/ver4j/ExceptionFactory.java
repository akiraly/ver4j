package com.github.ver4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

public class ExceptionFactory<T extends RuntimeException & IVerificationException> {
	private final String defaultMessageTemplate;

	private final Locale defaultLocale = Locale.ENGLISH;

	private final Class<T> exceptionType;

	private final Constructor<T> exceptionConstructor;

	public ExceptionFactory(Class<T> exceptionType,
			String defaultMessageTemplate) {
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

	public static <T extends RuntimeException & IVerificationException> ExceptionFactory<T> of(
			Class<T> exceptionType, String defaultMessageTemplate) {
		return new ExceptionFactory<T>(exceptionType, defaultMessageTemplate);
	}

	public void throwException(IVerifier verifier, Locale locale,
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

	private IllegalStateException failException(IVerifier verifier,
			String message, Exception cause) {
		return new IllegalStateException(
				String.format(
						defaultLocale,
						"Exception while building verification exception with type (%s) and message (%s) for verifier (%s).",
						exceptionType, message, verifier), cause);
	}

	public String getDefaultMessageTemplate() {
		return defaultMessageTemplate;
	}

	public Class<T> getExceptionType() {
		return exceptionType;
	}
}
