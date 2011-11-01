package com.github.ver4j;

import javax.annotation.Nonnull;

public class NumberVerifier<N extends Number & Comparable<N>> extends
		AOrderVerifierAwareVerifier<N> {

	public NumberVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull OrderVerifier<N> orderVerifier) {
		super(objectVerifier, orderVerifier);
	}

	/*
	 * isPositive, isNegative, isZero, notPositive, notNegative, notZero
	 * isInfinite, notInfinite, isNan, notNan, isSpecial, notSpecial
	 */
}
