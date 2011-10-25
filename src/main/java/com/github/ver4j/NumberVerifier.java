package com.github.ver4j;

import javax.annotation.Nonnull;

public class NumberVerifier<N extends Number & Comparable<N>> extends
		OrderVerifier<N> {

	public NumberVerifier(@Nonnull ObjectVerifier objectVerifier) {
		super(objectVerifier);
	}

	public void valueEquals() {

	}

	/*
	 * isPositive, isNegative, isZero, notPositive, notNegative, notZero
	 * isInfinite, notInfinite, isNan, notNan, isSpecial, notSpecial
	 */
}
