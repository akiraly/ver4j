package com.github.ver4j;

import javax.annotation.Nonnull;

public class TimeVerifier<C extends Comparable<C>> extends
		AOrderVerifierAwareVerifier<C> {

	public TimeVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull OrderVerifier<C> orderVerifier) {
		super(objectVerifier, orderVerifier);
	}

	/*
	 * before, after, notBefore, notAfter, hasUtcTimeZone, hasDefaultTimeZone
	 */
}
