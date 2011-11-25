package com.github.ver4j;

import javax.annotation.Nonnull;

public abstract class AObjectVerifierAwareVerifier<I> extends
		AInternalBasedVerifier<I> {
	@Nonnull
	private final ObjectVerifier objectVerifier;

	public AObjectVerifierAwareVerifier(@Nonnull ObjectVerifier objectVerifier) {
		super();
		this.objectVerifier = objectVerifier;
	}

	@Nonnull
	protected final ObjectVerifier object() {
		return objectVerifier;
	}

	@Nonnull
	protected final <T extends GeneralVerificationException> ExceptionFactory<T> exceptionFactoryOf(
			@Nonnull ExceptionTypeInfo<T> exceptionTypeInfo,
			@Nonnull String cause) {
		return exceptionFactoryOf(exceptionTypeInfo, objectVerifier
				.getDefaultExceptionMessageInfo().appendCause(cause));
	}

	@Override
	public final String getCategory() {
		return objectVerifier.getCategory();
	}
}
