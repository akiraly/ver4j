package com.github.ver4j;

import junit.framework.Assert;

import org.junit.Test;

import com.github.ver4j.ExceptionFactory;
import com.github.ver4j.ObjectVerifier;
import com.github.ver4j.exception.arg.ArgumentVerificationException;
import com.github.ver4j.exception.arg.NullPointerArgumentException;

public class ObjectVerifierTest {
	private final ObjectVerifier verifier = new ObjectVerifier(
			null,
			ExceptionFactory.of(ArgumentVerificationException.class, "Failed."),
			ExceptionFactory.of(NullPointerArgumentException.class, "Failed."));

	@Test
	public void testIsTrueWithTrue() {
		verifier.isTrue(true);
		verifier.isTrue(true, "");
		verifier.isTrue(true, "", null);
	}

	@Test
	public void testIsTrueWithFalseDisabled() {
		verifier.setEnabled(false);
		verifier.isTrue(false);
		verifier.isTrue(false, "");
		verifier.isTrue(false, "", null);
	}

	@Test(expected = ArgumentVerificationException.class)
	public void testIsTrueWithFalse1() {
		verifier.isTrue(false);
	}

	@Test(expected = ArgumentVerificationException.class)
	public void testIsTrueWithFalse2() {
		verifier.isTrue(false, "");
	}

	@Test(expected = ArgumentVerificationException.class)
	public void testIsTrueWithFalse3() {
		verifier.isTrue(false, "", null);
	}

	@Test
	public void testNotNullWithNotNull() {
		Assert.assertEquals("", verifier.notNull(""));
		Assert.assertEquals("", verifier.notNull("", ""));
		Assert.assertEquals("", verifier.notNull("", "", null));
	}

	@Test
	public void testNotNullWithNullDisabled() {
		verifier.setEnabled(false);
		Assert.assertEquals(null, verifier.notNull(null));
		Assert.assertEquals(null, verifier.notNull(null, ""));
		Assert.assertEquals(null, verifier.notNull(null, "", null));
	}

	@Test(expected = NullPointerArgumentException.class)
	public void testNotNullWithNull1() {
		verifier.notNull(null);
	}

	@Test(expected = NullPointerArgumentException.class)
	public void testNotNullWithNull2() {
		verifier.notNull(null, "");
	}

	@Test(expected = NullPointerArgumentException.class)
	public void testNotNullWithNull3() {
		verifier.notNull(null, "", null);
	}
}
