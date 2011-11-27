package com.github.ver4j;

import javax.annotation.Nonnull;

public class MethodInvocationParams {
	private final Object returnValue;
	private final Object[] args;
	private final Class<? extends GeneralVerificationException> expectedExceptionType;
	private final Class<? extends RuntimeException> expectedDisabledExceptionType;

	public MethodInvocationParams(Object returnValue, @Nonnull Object... args) {
		this(null, returnValue, args);
	}

	public MethodInvocationParams(
			Class<? extends GeneralVerificationException> expectedExceptionType,
			Object returnValue, @Nonnull Object... args) {
		this(expectedExceptionType, null, returnValue, args);
	}

	public MethodInvocationParams(
			Class<? extends GeneralVerificationException> expectedExceptionType,
			Class<? extends RuntimeException> expectedDisabledExceptionType,
			Object returnValue, @Nonnull Object... args) {
		super();
		this.returnValue = returnValue;
		this.args = args;
		this.expectedExceptionType = expectedExceptionType;
		this.expectedDisabledExceptionType = expectedDisabledExceptionType;
	}

	public boolean shouldPass() {
		return expectedExceptionType == null;
	}

	public Object getReturnValue() {
		return returnValue;
	}

	@Nonnull
	public Object[] getArgs() {
		return args;
	}

	public Class<? extends GeneralVerificationException> getExpectedExceptionType() {
		return expectedExceptionType;
	}

	public Class<? extends RuntimeException> getExpectedDisabledExceptionType() {
		return expectedDisabledExceptionType;
	}
}
