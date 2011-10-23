package com.github.ver4j;

import java.util.Arrays;

import org.junit.Test;

import com.github.ver4j.arg.exception.ArgumentTypeVerificationException;
import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.BatchArgumentVerificationException;
import com.github.ver4j.arg.exception.NullPointerArgumentVerificationException;

public class BatchVerifierTest {
	private final ObjectVerifier objectVerifier = new ObjectVerifier(null,
			new ExceptionMessageInfo("Test"),
			ExceptionTypeInfo.of(ArgumentVerificationException.class),
			ExceptionTypeInfo.of(ArgumentTypeVerificationException.class),
			ExceptionTypeInfo
					.of(NullPointerArgumentVerificationException.class));

	private final BatchVerifier verifier = new BatchVerifier(objectVerifier,
			ExceptionTypeInfo.of(BatchArgumentVerificationException.class));

	// notEmpty*

	@Test
	public void testNotEmptyWithTrue() {
		verifier.notEmpty(new Object[] { "" });
		verifier.notEmpty(new Object[] { "" }, "");
		verifier.notEmpty(new Object[] { "" }, "%s", "1");
		verifier.notEmptyCm(new Object[] { "" }, "", null);

		verifier.notEmpty(Arrays.asList(""));
		verifier.notEmpty(Arrays.asList(""), "");
		verifier.notEmpty(Arrays.asList(""), "%s", "1");
		verifier.notEmptyCm(Arrays.asList(""), "", null);
	}

	@Test
	public void testNotEmptyWithFalseDisabled() {
		verifier.setDisabled(true);
		verifier.notEmpty(new Object[0]);
		verifier.notEmpty(new Object[0], "");
		verifier.notEmpty(new Object[0], "%s", "1");
		verifier.notEmptyCm(new Object[0], "", null);

		verifier.notEmpty(Arrays.asList());
		verifier.notEmpty(Arrays.asList(), "");
		verifier.notEmpty(Arrays.asList(), "%s", "1");
		verifier.notEmptyCm(Arrays.asList(), "", null);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyWithFalse1() {
		verifier.notEmpty(new Object[0]);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyWithFalse2() {
		verifier.notEmpty(new Object[0], "");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyWithFalse3() {
		verifier.notEmpty(new Object[0], "%s", "1");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyCmWithFalse() {
		verifier.notEmptyCm(new Object[0], "", null);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyCollectionWithFalse1() {
		verifier.notEmpty(Arrays.asList());
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyCollectionWithFalse2() {
		verifier.notEmpty(Arrays.asList(), "");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyCollectionWithFalse3() {
		verifier.notEmpty(Arrays.asList(), "%s", "1");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyCmCollectionWithFalse() {
		verifier.notEmptyCm(Arrays.asList(), "", null);
	}

	// noNullElements*

	@Test
	public void testNoNullElementsWithTrue() {
		verifier.noNullElements(new Object[] { "" });
		verifier.noNullElements(new Object[] { "" }, "");
		verifier.noNullElements(new Object[] { "" }, "%s", "1");
		verifier.noNullElementsCm(new Object[] { "" }, "", null);

		verifier.noNullElements(Arrays.asList(""));
		verifier.noNullElements(Arrays.asList(""), "");
		verifier.noNullElements(Arrays.asList(""), "%s", "1");
		verifier.noNullElementsCm(Arrays.asList(""), "", null);
	}

	@Test
	public void testNoNullElementsWithFalseDisabled() {
		verifier.setDisabled(true);
		verifier.noNullElements(new Object[] { null });
		verifier.noNullElements(new Object[] { null }, "");
		verifier.noNullElements(new Object[] { null }, "%s", "1");
		verifier.noNullElementsCm(new Object[] { null }, "", null);

		verifier.noNullElements(Arrays.asList((Object) null));
		verifier.noNullElements(Arrays.asList((Object) null), "");
		verifier.noNullElements(Arrays.asList((Object) null), "%s", "1");
		verifier.noNullElementsCm(Arrays.asList((Object) null), "", null);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullElementsWithFalse1() {
		verifier.noNullElements(new Object[] { null });
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullElementsWithFalse2() {
		verifier.noNullElements(new Object[] { null }, "");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullElementsWithFalse3() {
		verifier.noNullElements(new Object[] { null }, "%s", "1");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullElementsCmWithFalse() {
		verifier.noNullElementsCm(new Object[] { null }, "", null);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullElementsIterableWithFalse1() {
		verifier.noNullElements(Arrays.asList((Object) null));
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullElementsIterableWithFalse2() {
		verifier.noNullElements(Arrays.asList((Object) null), "");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullElementsIterableWithFalse3() {
		verifier.noNullElements(Arrays.asList((Object) null), "%s", "1");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullElementsCmIterableWithFalse() {
		verifier.noNullElementsCm(Arrays.asList((Object) null), "", null);
	}
}
