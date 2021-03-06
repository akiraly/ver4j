package com.github.ver4j;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

public class ExceptionTypeInfoTest {
	@Test
	public void testWithCorrectExceptionType() throws InstantiationException,
			IllegalAccessException, InvocationTargetException {
		ExceptionTypeInfo<TestVerificationException> exceptionTypeInfo = new ExceptionTypeInfo<TestVerificationException>(
				TestVerificationException.class);
		String message = "message";
		String category = "category";

		TestVerificationException exception = exceptionTypeInfo.create(message,
				category, null);

		Assert.assertNotNull(exception);
		Assert.assertEquals(message, exception.getRawMessage());
		Assert.assertEquals(category, exception.getCategory());

		RuntimeException cause = new RuntimeException();
		exception = exceptionTypeInfo.create(message, category, cause);

		Assert.assertNotNull(exception);
		Assert.assertEquals(message, exception.getRawMessage());
		Assert.assertEquals(category, exception.getCategory());
		Assert.assertSame(cause, exception.getCause());
	}

	@Test(expected = IllegalStateException.class)
	public void testWithBrokenExceptionType() {
		new ExceptionTypeInfo<BrokenTestVerificationException>(
				BrokenTestVerificationException.class);
	}

	@Test(expected = InvocationTargetException.class)
	public void testWithBrokenExceptionType2() throws InstantiationException,
			IllegalAccessException, InvocationTargetException {
		ExceptionTypeInfo<BrokenTestVerificationException2> exceptionTypeInfo;
		try {
			exceptionTypeInfo = new ExceptionTypeInfo<BrokenTestVerificationException2>(
					BrokenTestVerificationException2.class);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		}
		exceptionTypeInfo.create("message", "category", null);
	}

	@Test(expected = IllegalStateException.class)
	public void testWithBrokenExceptionType3() {
		new ExceptionTypeInfo<BrokenTestVerificationException3>(
				BrokenTestVerificationException3.class);
	}
}
