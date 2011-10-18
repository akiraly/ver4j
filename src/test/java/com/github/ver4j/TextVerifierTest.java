package com.github.ver4j;

import org.junit.Test;

import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.ClassCastArgumentException;
import com.github.ver4j.arg.exception.NullPointerArgumentException;
import com.github.ver4j.arg.exception.TextArgumentException;

public class TextVerifierTest {
	private final ObjectVerifier objectVerifier = new ObjectVerifier(null,
			new ExceptionMessageInfo("Test"),
			ExceptionTypeInfo.of(ArgumentVerificationException.class),
			ExceptionTypeInfo.of(ClassCastArgumentException.class),
			ExceptionTypeInfo.of(NullPointerArgumentException.class));

	private final TextVerifier verifier = new TextVerifier(objectVerifier,
			ExceptionTypeInfo.of(TextArgumentException.class));

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

	@Test(expected = TextArgumentException.class)
	public void testNotEmptyWithFalse1() {
		verifier.notEmpty("");
	}

	@Test(expected = TextArgumentException.class)
	public void testNotEmptyWithFalse2() {
		verifier.notEmpty("", "");
	}

	@Test(expected = TextArgumentException.class)
	public void testNotEmptyWithFalse3() {
		verifier.notEmpty("", "%s", "1");
	}

	@Test(expected = TextArgumentException.class)
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

	@Test(expected = TextArgumentException.class)
	public void testNotBlankWithFalse1() {
		verifier.notBlank(" ");
	}

	@Test(expected = TextArgumentException.class)
	public void testNotBlankWithFalse2() {
		verifier.notBlank(" ", "");
	}

	@Test(expected = TextArgumentException.class)
	public void testNotBlankWithFalse3() {
		verifier.notBlank(" ", "%s", "1");
	}

	@Test(expected = TextArgumentException.class)
	public void testNotBlankCmWithFalse() {
		verifier.notBlankCm(" ", "", null);
	}
}
