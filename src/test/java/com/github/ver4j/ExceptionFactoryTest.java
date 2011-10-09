package com.github.ver4j;

import org.junit.Test;

import com.github.ver4j.ExceptionFactory;
import com.github.ver4j.IVerificationException;
import com.github.ver4j.IVerifier;

public class ExceptionFactoryTest {
	private static class TestVerificationException extends RuntimeException
			implements IVerificationException {
		private static final long serialVersionUID = 1L;

		private final String category;

		public TestVerificationException(String message, String category) {
			super(message);
			this.category = category;
		}

		@Override
		public final String getCategory() {
			return category;
		}

		@Override
		public String toString() {
			if (category != null) {
				return '[' + category + ']' + super.toString();
			}

			return super.toString();
		}
	}

	private static class BrokenTestVerificationException extends
			TestVerificationException {
		private static final long serialVersionUID = 1L;

		public BrokenTestVerificationException(String message) {
			super(message, null);
		}
	}

	private static class BrokenTestVerificationException2 extends
			TestVerificationException {
		private static final long serialVersionUID = 1L;

		public BrokenTestVerificationException2(String message, String category) {
			super(message, category);
			throw new UnsupportedOperationException("You shouldn't use me.");
		}
	}

	private static class DummyVerifier implements IVerifier {
		@Override
		public String getCategory() {
			return null;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}

		@Override
		public void setEnabled(boolean enabled) {
			// no-op
		}
	}

	@Test(expected = TestVerificationException.class)
	public void testWithCorrectExceptionType() {
		ExceptionFactory<TestVerificationException> exceptionFactory = new ExceptionFactory<TestVerificationException>(
				TestVerificationException.class, "Failed.");
		exceptionFactory.throwException(new DummyVerifier(), null, null,
				new Object[] { 1, 2 });
	}

	@Test(expected = IllegalStateException.class)
	public void testWithBrokenExceptionType() {
		new ExceptionFactory<BrokenTestVerificationException>(
				BrokenTestVerificationException.class, "Failed.");
	}

	@Test(expected = IllegalStateException.class)
	public void testWithBrokenExceptionType2() {
		ExceptionFactory<BrokenTestVerificationException2> exceptionFactory;
		try {
			exceptionFactory = new ExceptionFactory<BrokenTestVerificationException2>(
					BrokenTestVerificationException2.class, "Failed.");
		} catch (IllegalStateException e) {
			throw new RuntimeException(e);
		}
		exceptionFactory.throwException(new DummyVerifier(), null, null,
				new Object[] { 1, 2 });
	}

}
