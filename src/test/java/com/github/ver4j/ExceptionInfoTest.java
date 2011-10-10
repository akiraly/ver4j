package com.github.ver4j;

import java.lang.reflect.InvocationTargetException;

import org.junit.Assert;
import org.junit.Test;

public class ExceptionInfoTest {
	@Test
	public void testWithCorrectExceptionType() throws InstantiationException,
			IllegalAccessException, InvocationTargetException {
		ExceptionInfo<TestVerificationException> exceptionInfo = new ExceptionInfo<TestVerificationException>(
				TestVerificationException.class);
		String message = "message";
		String category = "category";

		TestVerificationException exception = exceptionInfo.create(message,
				category);

		Assert.assertNotNull(exception);
		Assert.assertEquals(message, exception.getMessage());
		Assert.assertEquals(category, exception.getCategory());
	}

	@Test(expected = IllegalStateException.class)
	public void testWithBrokenExceptionType() {
		new ExceptionInfo<BrokenTestVerificationException>(
				BrokenTestVerificationException.class);
	}

	@Test(expected = InvocationTargetException.class)
	public void testWithBrokenExceptionType2() throws InstantiationException,
			IllegalAccessException, InvocationTargetException {
		ExceptionInfo<BrokenTestVerificationException2> exceptionInfo;
		try {
			exceptionInfo = new ExceptionInfo<BrokenTestVerificationException2>(
					BrokenTestVerificationException2.class);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		}
		exceptionInfo.create("message", "category");
	}

}
