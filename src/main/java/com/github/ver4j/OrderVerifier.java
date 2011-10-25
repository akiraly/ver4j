package com.github.ver4j;

import javax.annotation.Nonnull;

public class OrderVerifier<C extends Comparable<C>> extends
		AObjectVerifierAwareVerifier {

	public OrderVerifier(@Nonnull ObjectVerifier objectVerifier) {
		super(objectVerifier);
	}

	/*
	 * inclusiveBetween, exclusiveBetween, in + range, greaterThan, lessThan,
	 * notGreaterThan, notLessThan, orderEquals, notOrderEquals
	 */
}
