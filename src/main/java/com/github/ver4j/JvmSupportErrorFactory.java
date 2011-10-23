package com.github.ver4j;

import java.io.UnsupportedEncodingException;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.CharEncoding;

public class JvmSupportErrorFactory {

	@Nonnull
	public SupportError of(CloneNotSupportedException cause) {
		return new SupportError(
				"Not supported cloning on a clone() implementing class instance?!",
				cause);
	}

	@Nonnull
	public SupportError of(@Nonnull String encoding,
			UnsupportedEncodingException cause) {
		return new SupportError("Not supported jvm encoding (" + encoding
				+ ")?!", cause);
	}

	@Nonnull
	public final SupportError ofUtf8Encoding(UnsupportedEncodingException cause) {
		return of(CharEncoding.UTF_8, cause);
	}

	@Nonnull
	public final SupportError ofIso88591Encoding(
			UnsupportedEncodingException cause) {
		return of(CharEncoding.ISO_8859_1, cause);
	}
}
