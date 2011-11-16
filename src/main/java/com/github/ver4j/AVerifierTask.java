package com.github.ver4j;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

public abstract class AVerifierTask<R> {
	public final R verify(@Nonnull ExceptionFactory<?> exceptionFactory,
			Locale locale, String errorMessageTemplate, Object errorMessage,
			Object[] errorMessageArgs) {
		RuntimeException cause;

		try {
			if (isValid())
				return result();
			cause = null;
		} catch (RuntimeException e) {
			cause = e;
		}

		throw exceptionFactory.newException(locale, errorMessageTemplate,
				errorMessage, errorMessageArgs, errorContext(), cause);
	}

	protected abstract R result();

	protected abstract boolean isValid();

	protected Map<Object, Object> errorContext() {
		return null;
	}

	@Nonnull
	protected Map<Object, Object> newContextMap() {
		return new LinkedHashMap<Object, Object>();
	}
}