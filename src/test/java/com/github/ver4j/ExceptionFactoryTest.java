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

		RuntimeException cause = null;

		TestVerificationException exception = exceptionFactory.createException(
				null, null, new Object[] { 1 }, null, null);
		checkException(exception, cause);

		Map<Object, Object> autoArgs = new LinkedHashMap<Object, Object>();
		autoArgs.put("key", "value");

		exception = exceptionFactory.newException();
		checkException(exception, cause);

		exception = exceptionFactory.newException(autoArgs, (Throwable) null);
		checkException(exception, cause);

		exception = exceptionFactory.newException(1, null);
		checkException(exception, cause);

		exception = exceptionFactory.newException(null, (Object[]) null);
		checkException(exception, cause);

		exception = exceptionFactory.newException(null, new Object[0]);
		checkException(exception, cause);

		exception = exceptionFactory.newException("", new Object[0]);
		checkException(exception, cause);

		exception = exceptionFactory.newException("%s", new Object[] { 1 });
		checkException(exception, cause);

		exception = exceptionFactory.newException("%s", new Object[] { 1 },
				autoArgs, null);
		checkException(exception, cause);

		exception = exceptionFactory.newExceptionCm("%s", null,
				new Object[] { 1 });
		checkException(exception, cause);

		exception = exceptionFactory.newExceptionCm("%s", null,
				new Object[] { 1 }, autoArgs, null);
		checkException(exception, cause);

		cause = new RuntimeException();

		exception = exceptionFactory.newException(null, cause);
		checkException(exception, cause);

		exception = exceptionFactory.newException("%s", new Object[] { 1 },
				autoArgs, cause);
		checkException(exception, cause);

		exception = exceptionFactory.newExceptionCm("%s", null,
				new Object[] { 1 }, autoArgs, cause);
		checkException(exception, cause);
	}

	private void checkException(TestVerificationException exception,
			Throwable cause) {
		Assert.assertNotNull(exception);
		Assert.assertNotNull(exception.getMessage());
		Assert.assertNotNull(exception.getCategory());
		Assert.assertSame(cause, exception.getCause());
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
		exceptionFactory.createException(null, null, null, null, null);
	}

}
