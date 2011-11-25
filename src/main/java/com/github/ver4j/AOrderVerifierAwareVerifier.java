package com.github.ver4j;

import javax.annotation.Nonnull;

public abstract class AOrderVerifierAwareVerifier<I, C extends Comparable<C>>
		extends AObjectVerifierAwareVerifier<I> {
	@Nonnull
	private final OrderVerifier<C> orderVerifier;

	public AOrderVerifierAwareVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull OrderVerifier<C> orderVerifier) {
		super(objectVerifier);
		this.orderVerifier = orderVerifier;
	}

	@Nonnull
	protected final OrderVerifier<C> order() {
		return orderVerifier;
	}
}
