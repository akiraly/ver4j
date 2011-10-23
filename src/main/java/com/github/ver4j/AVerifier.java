package com.github.ver4j;

import javax.annotation.Nonnull;

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

	@Nonnull
	protected final <T extends GeneralVerificationException> ExceptionFactory<T> exceptionFactoryOf(
			@Nonnull ExceptionTypeInfo<T> exceptionTypeInfo,
			@Nonnull ExceptionMessageInfo exceptionMessageInfo) {
		return ExceptionFactory.of(getCategory(), exceptionTypeInfo,
				exceptionMessageInfo);
	}
}
