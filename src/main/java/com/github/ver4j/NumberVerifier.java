package com.github.ver4j;

import javax.annotation.Nonnull;

import com.github.ver4j.NumberVerifier.NumberInternal;

public class NumberVerifier<N extends Number & Comparable<N>> extends
		AOrderVerifierAwareVerifier<NumberInternal, N> {

	public interface NumberInternal {
	}

	private class NotVerifyingInternal implements NumberInternal {
	}

	private class VerifyingInternal implements NumberInternal {
	}

	public NumberVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull OrderVerifier<N> orderVerifier) {
		super(objectVerifier, orderVerifier);
	}

	@Override
	protected NumberInternal newNotVerifyingInternal() {
		return new NotVerifyingInternal();
	}

	@Override
	protected NumberInternal newVerifyingInternal() {
		return new VerifyingInternal();
	}

	/*
	 * isPositive, isNegative, isZero, notPositive, notNegative, notZero
	 * isInfinite, notInfinite, isNan, notNan, isSpecial, notSpecial
	 */
}
