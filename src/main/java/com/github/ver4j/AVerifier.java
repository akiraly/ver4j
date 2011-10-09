package com.github.ver4j;

import java.util.Locale;

public abstract class AVerifier implements IVerifier {
	private boolean enabled = true;

	@Override
	public final boolean isEnabled() {
		return enabled;
	}

	@Override
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	protected final void verifyTrue(boolean expression,
			ExceptionFactory<?> exceptionFactory) {
		if (!expression) {
			exceptionFactory.throwException(this, null, null, null);
		}
	}

	protected final void verifyTrue(boolean expression,
			ExceptionFactory<?> exceptionFactory, Object errorMessage) {
		if (!expression) {
			exceptionFactory.throwException(this, null, null,
					new Object[] { errorMessage });
		}
	}

	protected final void verifyTrue(boolean expression,
			ExceptionFactory<?> exceptionFactory, String errorMessageTemplate,
			Locale locale, Object[] errorMessageArgs) {
		if (!expression) {
			exceptionFactory.throwException(this, locale, errorMessageTemplate,
					errorMessageArgs);
		}
	}
}
