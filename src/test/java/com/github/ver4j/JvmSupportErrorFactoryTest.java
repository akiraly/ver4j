package com.github.ver4j;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

public class JvmSupportErrorFactoryTest {
	private final JvmSupportErrorFactory factory = new JvmSupportErrorFactory();

	@Test
	public void testCloningException() {
		CloneNotSupportedException cause = new CloneNotSupportedException();
		SupportError se = factory.of(cause);
		checkSupportError(se, cause);
	}

	@Test
	public void testEncodingExceptions() {
		UnsupportedEncodingException cause = new UnsupportedEncodingException();
		SupportError se = factory.of("", cause);
		checkSupportError(se, cause);

		se = factory.ofUtf8Encoding(cause);
		checkSupportError(se, cause);

		se = factory.ofIso88591Encoding(cause);
		checkSupportError(se, cause);
	}

	private void checkSupportError(SupportError se, Throwable cause) {
		Assert.assertNotNull(se);
		Assert.assertNotNull(se.getMessage());
		Assert.assertSame(cause, se.getCause());
	}
}
