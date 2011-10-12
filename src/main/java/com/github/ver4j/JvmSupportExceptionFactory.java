package com.github.ver4j;

import java.io.UnsupportedEncodingException;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.CharEncoding;

public class JvmSupportExceptionFactory {

	@Nonnull
	public SupportException of(CloneNotSupportedException cause) {
		return new SupportException(
				"Not supported cloning on a clone() implementing class instance?!",
				cause);
	}

	@Nonnull
	public SupportException of(@Nonnull String encoding,
			UnsupportedEncodingException cause) {
		return new SupportException("Not supported jvm encoding (" + encoding
				+ ")?!", cause);
	}

	@Nonnull
	public final SupportException ofUtf8Encoding(
			UnsupportedEncodingException cause) {
		return of(CharEncoding.UTF_8, cause);
	}

	@Nonnull
	public final SupportException ofIso88591Encoding(
			UnsupportedEncodingException cause) {
		return of(CharEncoding.ISO_8859_1, cause);
	}
}
