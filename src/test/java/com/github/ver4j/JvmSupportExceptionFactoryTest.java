package com.github.ver4j;

import java.io.UnsupportedEncodingException;

import junit.framework.Assert;

import org.junit.Test;

public class JvmSupportExceptionFactoryTest {
	private final JvmSupportExceptionFactory factory = new JvmSupportExceptionFactory();

	@Test
	public void testCloningException() {
		CloneNotSupportedException cause = new CloneNotSupportedException();
		SupportException se = factory.of(cause);
		checkSupportException(se, cause);
	}

	@Test
	public void testEncodingExceptions() {
		UnsupportedEncodingException cause = new UnsupportedEncodingException();
		SupportException se = factory.of("", cause);
		checkSupportException(se, cause);

		se = factory.ofUtf8Encoding(cause);
		checkSupportException(se, cause);

		se = factory.ofIso88591Encoding(cause);
		checkSupportException(se, cause);
	}

	private void checkSupportException(SupportException se, Throwable cause) {
		Assert.assertNotNull(se);
		Assert.assertNotNull(se.getMessage());
		Assert.assertSame(cause, se.getCause());
	}
}
