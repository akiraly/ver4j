package com.github.ver4j;

import javax.annotation.Nonnull;

public abstract class AInternalBasedVerifier<I> extends AVerifier {
	private I internal = newVerifyingInternal();

	@Override
	public void setDisabled(boolean disabled) {
		if (isDisabled() == disabled)
			return;

		super.setDisabled(disabled);

		if (disabled)
			internal = newNotVerifyingInternal();
		else
			internal = newVerifyingInternal();
	}

	@Nonnull
	public I internal() {
		return internal;
	}

	protected abstract I newNotVerifyingInternal();

	protected abstract I newVerifyingInternal();

	@Nonnull
	protected final <T extends GeneralVerificationException> ExceptionFactory<T> exceptionFactoryOf(
			@Nonnull ExceptionTypeInfo<T> exceptionTypeInfo,
			@Nonnull ExceptionMessageInfo exceptionMessageInfo) {
		return ExceptionFactory.of(getCategory(), exceptionTypeInfo,
				exceptionMessageInfo);
	}
}
