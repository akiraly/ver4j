package com.github.ver4j;

public class SupportException extends IllegalStateException {

	private static final long serialVersionUID = 1L;

	public static final JvmSupportExceptionFactory jvm = new JvmSupportExceptionFactory();

	public SupportException(String message, Throwable cause) {
		super(message, cause);
	}

}
