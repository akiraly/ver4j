package com.github.ver4j;

import java.util.Locale;

public abstract class AVerifier implements IVerifier {
	private boolean disabled = false;

	@Override
	public final boolean isDisabled() {
		return disabled;
	}

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	protected final void fail(ExceptionInfo<?> exceptionInfo, String cause) {
		throw exception(exceptionInfo, cause);
	}

	protected final void fail(ExceptionInfo<?> exceptionInfo, String cause,
			Object errorMessage) {
		throw exception(exceptionInfo, cause, errorMessage);
	}

	protected final void fail(ExceptionInfo<?> exceptionInfo,
			String errorMessageTemplate, Locale locale,
			Object[] errorMessageArgs) {
		throw exception(exceptionInfo, errorMessageTemplate, locale,
				errorMessageArgs);
	}

	protected final RuntimeException exception(ExceptionInfo<?> exceptionInfo,
			String cause) {
		return getExceptionFactory().createException(this, exceptionInfo, null,
				null, new Object[] { null, cause });
	}

	protected final RuntimeException exception(ExceptionInfo<?> exceptionInfo,
			String cause, Object errorMessage) {
		return getExceptionFactory().createException(this, exceptionInfo, null,
				null, new Object[] { errorMessage, cause });
	}

	protected final RuntimeException exception(ExceptionInfo<?> exceptionInfo,
			String errorMessageTemplate, Locale locale,
			Object[] errorMessageArgs) {
		return getExceptionFactory().createException(this, exceptionInfo,
				locale, errorMessageTemplate, errorMessageArgs);

	}
}
