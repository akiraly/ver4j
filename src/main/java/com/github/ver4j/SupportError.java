package com.github.ver4j;

import java.io.UnsupportedEncodingException;

import javax.annotation.Nonnull;

public class SupportError extends Error {

	private static final long serialVersionUID = 1L;

	private static final JvmSupportErrorFactory JVM = new JvmSupportErrorFactory();

	public SupportError(String message, Throwable cause) {
		super(message, cause);
	}

	@Nonnull
	public static SupportError of(CloneNotSupportedException cause) {
		return JVM.of(cause);
	}

	@Nonnull
	public static SupportError of(@Nonnull String encoding,
			UnsupportedEncodingException cause) {
		return JVM.of(encoding, cause);
	}

	@Nonnull
	public static SupportError ofUtf8Encoding(UnsupportedEncodingException cause) {
		return JVM.ofUtf8Encoding(cause);
	}

	@Nonnull
	public static SupportError ofIso88591Encoding(
			UnsupportedEncodingException cause) {
		return JVM.ofIso88591Encoding(cause);
	}

}
