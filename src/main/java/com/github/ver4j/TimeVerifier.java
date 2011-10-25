package com.github.ver4j;

import javax.annotation.Nonnull;

public class TimeVerifier<C extends Comparable<C>> extends OrderVerifier<C> {

	public TimeVerifier(@Nonnull ObjectVerifier objectVerifier) {
		super(objectVerifier);
	}

	/*
	 * before, after, notBefore, notAfter, hasUtcTimeZone, hasDefaultTimeZone
	 */
}
