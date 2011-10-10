package com.github.ver4j;

import org.junit.Assert;
import org.junit.Test;

public class ExceptionFactoryTest {

	@Test
	public void testWithCorrectExceptionType() {
		ExceptionFactory<TestVerificationException> exceptionFactory = ExceptionFactory
				.of("category",
						ExceptionTypeInfo.of(TestVerificationException.class),
						new ExceptionMessageInfo("Test"));

		TestVerificationException exception = exceptionFactory.createException(
				null, null, new Object[] { 1 });

		Assert.assertNotNull(exception);
		Assert.assertNotNull(exception.getMessage());
		Assert.assertNotNull(exception.getCategory());
	}

	@Test(expected = IllegalStateException.class)
	public void testWithBrokenExceptionType2() {
		ExceptionTypeInfo<BrokenTestVerificationException2> exceptionInfo;

		try {
			exceptionInfo = new ExceptionTypeInfo<BrokenTestVerificationException2>(
					BrokenTestVerificationException2.class);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		}

		ExceptionFactory<BrokenTestVerificationException2> exceptionFactory = ExceptionFactory
				.of(null, exceptionInfo, new ExceptionMessageInfo("Test"));
		exceptionFactory.createException(null, null, new Object[] { 1 });
	}

}
