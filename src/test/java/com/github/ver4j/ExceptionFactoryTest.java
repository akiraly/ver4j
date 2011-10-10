package com.github.ver4j;

import org.junit.Assert;
import org.junit.Test;

public class ExceptionFactoryTest {
	private static class DummyVerifier implements IVerifier {
		private final ExceptionFactory exceptionFactory;

		public DummyVerifier(ExceptionFactory exceptionFactory) {
			super();
			this.exceptionFactory = exceptionFactory;
		}

		@Override
		public String getCategory() {
			return "category";
		}

		@Override
		public boolean isDisabled() {
			return false;
		}

		@Override
		public void setDisabled(boolean disabled) {
			// no-op
		}

		@Override
		public ExceptionFactory getExceptionFactory() {
			return exceptionFactory;
		}
	}

	@Test
	public void testWithCorrectExceptionType() {
		ExceptionFactory exceptionFactory = new ExceptionFactory("Test");
		ExceptionInfo<TestVerificationException> exceptionInfo = new ExceptionInfo<TestVerificationException>(
				TestVerificationException.class);

		TestVerificationException exception = exceptionFactory.createException(
				new DummyVerifier(exceptionFactory), exceptionInfo, null, null,
				new Object[] { 1, 2 });

		Assert.assertNotNull(exception);
		Assert.assertNotNull(exception.getMessage());
		Assert.assertNotNull(exception.getCategory());
	}

	@Test(expected = IllegalStateException.class)
	public void testWithBrokenExceptionType2() {
		ExceptionFactory exceptionFactory = new ExceptionFactory("Test");
		ExceptionInfo<BrokenTestVerificationException2> exceptionInfo;

		try {
			exceptionInfo = new ExceptionInfo<BrokenTestVerificationException2>(
					BrokenTestVerificationException2.class);
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		}
		exceptionFactory.createException(new DummyVerifier(exceptionFactory),
				exceptionInfo, null, null, new Object[] { 1, 2 });
	}

}
