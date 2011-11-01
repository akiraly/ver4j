package com.github.ver4j;

import junit.framework.Assert;

import org.apache.commons.lang3.Range;
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
		verifier.isBefore(1, 0, "");
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
		verifier.isAfter(1, 2, "");
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
		verifier.notBefore(1, 2, "");
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
		verifier.notAfter(1, 0, "");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotAfterWithFalse3() {
		verifier.notAfter(1, 0, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotAfterCmWithFalse() {
		verifier.notAfterCm(1, 0, "", null);
	}

	// equals*

	@Test
	public void testEqualsWithTrue() {
		Integer i = 1;
		Integer r = 1;
		Assert.assertSame(i, verifier.equals(i, r));
		Assert.assertSame(i, verifier.equals(i, r));
		Assert.assertSame(i, verifier.equals(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.equalsCm(i, r, "", null));
	}

	@Test
	public void testEqualsWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 1;
		Integer r = 0;
		Assert.assertSame(i, verifier.equals(i, r));
		Assert.assertSame(i, verifier.equals(i, r));
		Assert.assertSame(i, verifier.equals(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.equalsCm(i, r, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testEqualsWithFalse1() {
		verifier.equals(1, 0);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testEqualsWithFalse2() {
		verifier.equals(1, 0, "");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testEqualsWithFalse3() {
		verifier.equals(1, 0, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testEqualsCmWithFalse() {
		verifier.equalsCm(1, 0, "", null);
	}

	// notEqual*

	@Test
	public void testNotEqualWithTrue() {
		Integer i = 1;
		Integer r = 0;
		Assert.assertSame(i, verifier.notEqual(i, r));
		Assert.assertSame(i, verifier.notEqual(i, r));
		Assert.assertSame(i, verifier.notEqual(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.notEqualCm(i, r, "", null));
	}

	@Test
	public void testNotEqualWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 1;
		Integer r = 1;
		Assert.assertSame(i, verifier.notEqual(i, r));
		Assert.assertSame(i, verifier.notEqual(i, r));
		Assert.assertSame(i, verifier.notEqual(i, r, "%s", "1"));
		Assert.assertSame(i, verifier.notEqualCm(i, r, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotEqualWithFalse1() {
		verifier.notEqual(1, 1);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotEqualWithFalse2() {
		verifier.notEqual(1, 1, "");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotEqualWithFalse3() {
		verifier.notEqual(1, 1, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testNotEqualCmWithFalse() {
		verifier.notEqualCm(1, 1, "", null);
	}

	// inclusiveBetween*

	@Test
	public void testInclusiveBetweenWithTrue() {
		Integer i = 1;
		Integer min = 1;
		Integer max = 2;
		Assert.assertSame(i, verifier.inclusiveBetween(i, min, max));
		Assert.assertSame(i, verifier.inclusiveBetween(i, min, max));
		Assert.assertSame(i, verifier.inclusiveBetween(i, min, max, "%s", "1"));
		Assert.assertSame(i, verifier.inclusiveBetweenCm(i, min, max, "", null));
	}

	@Test
	public void testInclusiveBetweenWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 0;
		Integer min = 1;
		Integer max = 2;
		Assert.assertSame(i, verifier.inclusiveBetween(i, min, max));
		Assert.assertSame(i, verifier.inclusiveBetween(i, min, max));
		Assert.assertSame(i, verifier.inclusiveBetween(i, min, max, "%s", "1"));
		Assert.assertSame(i, verifier.inclusiveBetweenCm(i, min, max, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testInclusiveBetweenWithFalse1() {
		verifier.inclusiveBetween(0, 1, 2);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testInclusiveBetweenWithFalse2() {
		verifier.inclusiveBetween(0, 1, 2, "");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testInclusiveBetweenWithFalse3() {
		verifier.inclusiveBetween(0, 1, 2, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testInclusiveBetweenCmWithFalse() {
		verifier.inclusiveBetweenCm(0, 1, 2, "", null);
	}

	// exclusiveBetween*

	@Test
	public void testExclusiveBetweenWithTrue() {
		Integer i = 1;
		Integer min = 0;
		Integer max = 2;
		Assert.assertSame(i, verifier.exclusiveBetween(i, min, max));
		Assert.assertSame(i, verifier.exclusiveBetween(i, min, max));
		Assert.assertSame(i, verifier.exclusiveBetween(i, min, max, "%s", "1"));
		Assert.assertSame(i, verifier.exclusiveBetweenCm(i, min, max, "", null));
	}

	@Test
	public void testExclusiveBetweenWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 0;
		Integer min = 0;
		Integer max = 2;
		Assert.assertSame(i, verifier.exclusiveBetween(i, min, max));
		Assert.assertSame(i, verifier.exclusiveBetween(i, min, max));
		Assert.assertSame(i, verifier.exclusiveBetween(i, min, max, "%s", "1"));
		Assert.assertSame(i, verifier.exclusiveBetweenCm(i, min, max, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testExclusiveBetweenWithFalse1() {
		verifier.exclusiveBetween(0, 0, 2);
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testExclusiveBetweenWithFalse2() {
		verifier.exclusiveBetween(0, 0, 2, "");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testExclusiveBetweenWithFalse3() {
		verifier.exclusiveBetween(0, 0, 2, "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testExclusiveBetweenCmWithFalse() {
		verifier.exclusiveBetweenCm(0, 0, 2, "", null);
	}

	// inRange*

	@Test
	public void testInRangeWithTrue() {
		Integer i = 1;
		Range<Integer> range = Range.between(1, 2);
		Assert.assertSame(i, verifier.inRange(i, range));
		Assert.assertSame(i, verifier.inRange(i, range));
		Assert.assertSame(i, verifier.inRange(i, range, "%s", "1"));
		Assert.assertSame(i, verifier.inRangeCm(i, range, "", null));
	}

	@Test
	public void testInRangeWithFalseDisabled() {
		verifier.setDisabled(true);
		Integer i = 0;
		Range<Integer> range = Range.between(1, 2);
		Assert.assertSame(i, verifier.inRange(i, range));
		Assert.assertSame(i, verifier.inRange(i, range));
		Assert.assertSame(i, verifier.inRange(i, range, "%s", "1"));
		Assert.assertSame(i, verifier.inRangeCm(i, range, "", null));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testInRangeWithFalse1() {
		verifier.inRange(0, Range.between(1, 2));
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testInRangeWithFalse2() {
		verifier.inRange(0, Range.between(1, 2), "");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testInRangeWithFalse3() {
		verifier.inRange(0, Range.between(1, 2), "%s", "1");
	}

	@Test(expected = ArgumentOrderVerificationException.class)
	public void testInRangeCmWithFalse() {
		verifier.inRangeCm(0, Range.between(1, 2), "", null);
	}

}
