package com.github.ver4j;

import javax.annotation.Nonnull;

import com.github.ver4j.TimeVerifier.TimeInternal;

public class TimeVerifier<C extends Comparable<C>> extends
		AOrderVerifierAwareVerifier<TimeInternal, C> {

	public interface TimeInternal {
	}

	private class NotVerifyingInternal implements TimeInternal {
	}

	private class VerifyingInternal implements TimeInternal {
	}

	public TimeVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull OrderVerifier<C> orderVerifier) {
		super(objectVerifier, orderVerifier);
	}

	@Override
	protected TimeInternal newNotVerifyingInternal() {
		return new NotVerifyingInternal();
	}

	@Override
	protected TimeInternal newVerifyingInternal() {
		return new VerifyingInternal();
	}

	/*
	 * before, after, notBefore, notAfter, hasUtcTimeZone, hasDefaultTimeZone
	 */
}
