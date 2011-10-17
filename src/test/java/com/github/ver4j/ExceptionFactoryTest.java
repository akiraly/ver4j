package com.github.ver4j;

import java.util.LinkedHashMap;
import java.util.Map;

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
				null, null, new Object[] { 1 }, null);
		checkException(exception);

		Map<Object, Object> autoArgs = new LinkedHashMap<Object, Object>();
		autoArgs.put("key", "value");

		exception = exceptionFactory.newException();
		checkException(exception);

		exception = exceptionFactory.newException(autoArgs);
		checkException(exception);

		exception = exceptionFactory.newException(1, null);
		checkException(exception);

		exception = exceptionFactory.newException(null, null);
		checkException(exception);

		exception = exceptionFactory.newException(null, new Object[0]);
		checkException(exception);

		exception = exceptionFactory.newException("", new Object[0]);
		checkException(exception);

		exception = exceptionFactory.newException("%s", new Object[] { 1 });
		checkException(exception);

		exception = exceptionFactory.newException("%s", new Object[] { 1 },
				autoArgs);
		checkException(exception);

		exception = exceptionFactory.newExceptionCm("%s", null,
				new Object[] { 1 });
		checkException(exception);
	}

	private void checkException(TestVerificationException exception) {
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
		exceptionFactory.createException(null, null, null, null);
	}

}
