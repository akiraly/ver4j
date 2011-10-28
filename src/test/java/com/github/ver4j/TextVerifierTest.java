package com.github.ver4j;

import junit.framework.Assert;

import org.junit.Test;

import com.github.ver4j.arg.exception.ArgumentTypeVerificationException;
import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.NullPointerArgumentVerificationException;
import com.github.ver4j.arg.exception.TextArgumentVerificationException;

public class TextVerifierTest {
	private final ObjectVerifier objectVerifier = new ObjectVerifier(null,
			new ExceptionMessageInfo("Test"),
			ExceptionTypeInfo.of(ArgumentVerificationException.class),
			ExceptionTypeInfo.of(ArgumentTypeVerificationException.class),
			ExceptionTypeInfo
					.of(NullPointerArgumentVerificationException.class));

	private final TextVerifier<CharSequence> verifier = new TextVerifier<CharSequence>(
			objectVerifier,
			ExceptionTypeInfo.of(TextArgumentVerificationException.class));

	// notEmpty*

	@Test
	public void testNotEmptyWithTrue() {
		Assert.assertEquals(" ", verifier.notEmpty(" "));
		Assert.assertEquals(" ", verifier.notEmpty(" ", ""));
		Assert.assertEquals(" ", verifier.notEmpty(" ", "%s", "1"));
		Assert.assertEquals(" ", verifier.notEmptyCm(" ", "", null));
	}

	@Test
	public void testNotEmptyWithFalseDisabled() {
		verifier.setDisabled(true);
		Assert.assertEquals("", verifier.notEmpty(""));
		Assert.assertEquals("", verifier.notEmpty("", ""));
		Assert.assertEquals("", verifier.notEmpty("", "%s", "1"));
		Assert.assertEquals("", verifier.notEmptyCm("", "", null));
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testNotEmptyWithFalse1() {
		verifier.notEmpty("");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testNotEmptyWithFalse2() {
		verifier.notEmpty("", "");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testNotEmptyWithFalse3() {
		verifier.notEmpty("", "%s", "1");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testNotEmptyCmWithFalse() {
		verifier.notEmptyCm("", "", null);
	}

	// notBlank*

	@Test
	public void testNotBlankWithTrue() {
		Assert.assertEquals(" a ", verifier.notBlank(" a "));
		Assert.assertEquals(" a ", verifier.notBlank(" a ", ""));
		Assert.assertEquals(" a ", verifier.notBlank(" a ", "%s", "1"));
		Assert.assertEquals(" a ", verifier.notBlankCm(" a ", "", null));
	}

	@Test
	public void testNotBlankWithFalseDisabled() {
		verifier.setDisabled(true);
		Assert.assertEquals(" ", verifier.notBlank(" "));
		Assert.assertEquals(" ", verifier.notBlank(" ", ""));
		Assert.assertEquals(" ", verifier.notBlank(" ", "%s", "1"));
		Assert.assertEquals(" ", verifier.notBlankCm(" ", "", null));
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testNotBlankWithFalse1() {
		verifier.notBlank(" ");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testNotBlankWithFalse2() {
		verifier.notBlank(" ", "");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testNotBlankWithFalse3() {
		verifier.notBlank(" ", "%s", "1");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testNotBlankCmWithFalse() {
		verifier.notBlankCm(" ", "", null);
	}
}
