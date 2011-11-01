package com.github.ver4j;

import junit.framework.Assert;

import org.junit.Test;

import com.github.ver4j.arg.exception.ArgumentOrderVerificationException;
import com.github.ver4j.arg.exception.ArgumentTypeVerificationException;
import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.NullPointerArgumentVerificationException;

public class OrderVerifierTest {
	private final ObjectVerifier objectVerifier = new ObjectVerifier(null,
			new ExceptionMessageInfo("Test"),
			ExceptionTypeInfo.of(ArgumentVerificationException.class),
			ExceptionTypeInfo.of(ArgumentTypeVerificationException.class),
			ExceptionTypeInfo
					.of(NullPointerArgumentVerificationException.class));

	private final OrderVerifier<Integer> verifier = new OrderVerifier<Integer>(
			objectVerifier,
			ExceptionTypeInfo.of(ArgumentOrderVerificationException.class));

	// isBefore*

	@Test
	public void testIsBeforeWithTrue() {
		Integer i = 1;
		Integer r = 2;
		Assert.assertSame(i, verifier.isBefore(i, r));
		Assert.assertSame(i, verifier.isBefore(i, r));
		Assert.assertSame(i, verifier.isBefore(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.isBeforeCm(i, r, "", null));
	}

	@Test
	public void testIsBeforeWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 1;
		Integer r = 0;
		Assert.assertSame(i, verifier.isBefore(i, r));
		Assert.assertSame(i, verifier.isBefore(i, r));
		Assert.assertSame(i, verifier.isBefore(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.isBeforeCm(i, r, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testIsBeforeWithFalse1() {
		verifier.isBefore(1, 0);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testIsBeforeWithFalse2() {
		verifier.isBefore(1, 0);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testIsBeforeWithFalse3() {
		verifier.isBefore(1, 0, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testIsBeforeCmWithFalse() {
		verifier.isBefore(1, 0, null);
	}
}
