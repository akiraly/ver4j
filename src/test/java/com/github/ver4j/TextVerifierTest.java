package com.github.ver4j;

import junit.framework.Assert;

import org.junit.Test;

import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.ArgumentTypeVerificationException;
import com.github.ver4j.arg.exception.NullPointerArgumentVerificationException;
import com.github.ver4j.arg.exception.TextArgumentVerificationException;

public class TextVerifierTest {
	private final ObjectVerifier objectVerifier = new ObjectVerifier(null,
			new ExceptionMessageInfo("Test"),
			ExceptionTypeInfo.of(ArgumentVerificationException.class),
			ExceptionTypeInfo.of(ArgumentTypeVerificationException.class),
			ExceptionTypeInfo.of(NullPointerArgumentVerificationException.class));

	private final TextVerifier verifier = new TextVerifier(objectVerifier,
			ExceptionTypeInfo.of(TextArgumentVerificationException.class));

	// notEmpty*

	@Test
	public void testNotEmptyWithTrue() {
		verifier.notEmpty(" ");
		verifier.notEmpty(" ", "");
		verifier.notEmpty(" ", "%s", "1");
		verifier.notEmptyCm(" ", "", null);
	}

	@Test
	public void testNotEmptyWithFalseDisabled() {
		verifier.setDisabled(true);
		verifier.notEmpty("");
		verifier.notEmpty("", "");
		verifier.notEmpty("", "%s", "1");
		verifier.notEmptyCm("", "", null);
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
		verifier.notBlank(" a ");
		verifier.notBlank(" a ", "");
		verifier.notBlank(" a ", "%s", "1");
		verifier.notBlankCm(" a ", "", null);
	}

	@Test
	public void testNotBlankWithFalseDisabled() {
		verifier.setDisabled(true);
		verifier.notBlank(" ");
		verifier.notBlank(" ", "");
		verifier.notBlank(" ", "%s", "1");
		verifier.notBlankCm(" ", "", null);
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

	// strippedNotNull*

	@Test
	public void testStrippedNotNullWithTrue() {
		Assert.assertEquals("a", verifier.strippedNotNull(" a "));
		Assert.assertEquals("a", verifier.strippedNotNull(" a ", ""));
		Assert.assertEquals("a", verifier.strippedNotNull(" a ", "%s", "1"));
		Assert.assertEquals("a", verifier.strippedNotNullCm(" a ", "", null));
	}

	@Test
	public void testStrippedNotNullWithFalseDisabled() {
		verifier.setDisabled(true);
		Assert.assertEquals(null, verifier.strippedNotNull(" "));
		Assert.assertEquals(null, verifier.strippedNotNull(" ", ""));
		Assert.assertEquals(null, verifier.strippedNotNull(" ", "%s", "1"));
		Assert.assertEquals(null, verifier.strippedNotNullCm(" ", "", null));
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testStrippedNotNullWithFalse1() {
		verifier.strippedNotNull(" ");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testStrippedNotNullWithFalse2() {
		verifier.strippedNotNull(" ", "");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testStrippedNotNullWithFalse3() {
		verifier.strippedNotNull(" ", "%s", "1");
	}

	@Test(expected = TextArgumentVerificationException.class)
	public void testStrippedNotNullCmWithFalse() {
		verifier.strippedNotNullCm(" ", "", null);
	}
}
