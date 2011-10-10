package com.github.ver4j.state;

import java.io.UnsupportedEncodingException;
import java.util.Locale;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.CharEncoding;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionFactory;
import com.github.ver4j.ExceptionInfo;
import com.github.ver4j.state.exception.NullPointerStateException;
import com.github.ver4j.state.exception.StateVerificationException;

public class StateCompositeVerifier extends CompositeVerifier {

	public StateCompositeVerifier(String category) {
		super(category, //
				new ExceptionFactory("State"), //
				ExceptionInfo.of(StateVerificationException.class), //
				ExceptionInfo.of(NullPointerStateException.class) //
		);
	}

	public void failedCloningSupport(CloneNotSupportedException cause) {
		throw new IllegalStateException(
				"Not supported cloning on a clone supporting object?!", cause);
	}

	public void failedJvmEncodingSupport(@Nonnull String encoding,
			UnsupportedEncodingException cause) {
		throw new IllegalStateException(String.format(Locale.ENGLISH,
				"Not supported jvm encoding (%s)?!", encoding), cause);
	}

	public final void failedUtf8EncodingSupport(
			UnsupportedEncodingException cause) {
		failedJvmEncodingSupport(CharEncoding.UTF_8, cause);
	}

	public final void failedIso88591EncodingSupport(
			UnsupportedEncodingException cause) {
		failedJvmEncodingSupport(CharEncoding.ISO_8859_1, cause);
	}

}
