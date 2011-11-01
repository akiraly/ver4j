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
		verifier.isBeforeCm(1, 0, "", null);
	}

	// isAfter*

	@Test
	public void testIsAfterWithTrue() {
		Integer i = 1;
		Integer r = 0;
		Assert.assertSame(i, verifier.isAfter(i, r));
		Assert.assertSame(i, verifier.isAfter(i, r));
		Assert.assertSame(i, verifier.isAfter(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.isAfterCm(i, r, "", null));
	}

	@Test
	public void testIsAfterWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 1;
		Integer r = 2;
		Assert.assertSame(i, verifier.isAfter(i, r));
		Assert.assertSame(i, verifier.isAfter(i, r));
		Assert.assertSame(i, verifier.isAfter(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.isAfterCm(i, r, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testIsAfterWithFalse1() {
		verifier.isAfter(1, 2);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testIsAfterWithFalse2() {
		verifier.isAfter(1, 2);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testIsAfterWithFalse3() {
		verifier.isAfter(1, 2, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testIsAfterCmWithFalse() {
		verifier.isAfterCm(1, 2, "", null);
	}

	// notBefore*

	@Test
	public void testNotBeforeWithTrue() {
		Integer i = 1;
		Integer r = 0;
		Assert.assertSame(i, verifier.notBefore(i, r));
		Assert.assertSame(i, verifier.notBefore(i, r));
		Assert.assertSame(i, verifier.notBefore(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.notBeforeCm(i, r, "", null));
	}

	@Test
	public void testNotBeforeWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 1;
		Integer r = 2;
		Assert.assertSame(i, verifier.notBefore(i, r));
		Assert.assertSame(i, verifier.notBefore(i, r));
		Assert.assertSame(i, verifier.notBefore(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.notBeforeCm(i, r, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotBeforeWithFalse1() {
		verifier.notBefore(1, 2);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotBeforeWithFalse2() {
		verifier.notBefore(1, 2);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotBeforeWithFalse3() {
		verifier.notBefore(1, 2, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotBeforeCmWithFalse() {
		verifier.notBeforeCm(1, 2, "", null);
	}

	// notAfter*

	@Test
	public void testNotAfterWithTrue() {
		Integer i = 1;
		Integer r = 2;
		Assert.assertSame(i, verifier.notAfter(i, r));
		Assert.assertSame(i, verifier.notAfter(i, r));
		Assert.assertSame(i, verifier.notAfter(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.notAfterCm(i, r, "", null));
	}

	@Test
	public void testNotAfterWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 1;
		Integer r = 0;
		Assert.assertSame(i, verifier.notAfter(i, r));
		Assert.assertSame(i, verifier.notAfter(i, r));
		Assert.assertSame(i, verifier.notAfter(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.notAfterCm(i, r, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotAfterWithFalse1() {
		verifier.notAfter(1, 0);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotAfterWithFalse2() {
		verifier.notAfter(1, 0);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotAfterWithFalse3() {
		verifier.notAfter(1, 0, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotAfterCmWithFalse() {
		verifier.notAfterCm(1, 0, "", null);
	}

}
