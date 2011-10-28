package com.github.ver4j;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
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
		Object[] array = new Object[] { "" };
		Assert.assertSame(array, verifier.notEmpty(array));
		Assert.assertSame(array, verifier.notEmpty(array, ""));
		Assert.assertSame(array, verifier.notEmpty(array, "%s", "1"));
		Assert.assertSame(array, verifier.notEmptyCm(array, "", null));

		List<String> list = Arrays.asList("");
		Assert.assertSame(list, verifier.notEmpty(list));
		Assert.assertSame(list, verifier.notEmpty(list, ""));
		Assert.assertSame(list, verifier.notEmpty(list, "%s", "1"));
		Assert.assertSame(list, verifier.notEmptyCm(list, "", null));

		Map<String, String> map = new HashMap<String, String>();
		map.put("", "");
		Assert.assertSame(map, verifier.notEmpty(map));
		Assert.assertSame(map, verifier.notEmpty(map, ""));
		Assert.assertSame(map, verifier.notEmpty(map, "%s", "1"));
		Assert.assertSame(map, verifier.notEmptyCm(map, "", null));
	}

	@Test
	public void testNotEmptyWithFalseDisabled() {
		verifier.setDisabled(true);

		Object[] array = new Object[0];
		Assert.assertSame(array, verifier.notEmpty(array));
		Assert.assertSame(array, verifier.notEmpty(array, ""));
		Assert.assertSame(array, verifier.notEmpty(array, "%s", "1"));
		Assert.assertSame(array, verifier.notEmptyCm(array, "", null));

		List<Object> list = Arrays.asList();
		Assert.assertSame(list, verifier.notEmpty(list));
		Assert.assertSame(list, verifier.notEmpty(list, ""));
		Assert.assertSame(list, verifier.notEmpty(list, "%s", "1"));
		Assert.assertSame(list, verifier.notEmptyCm(list, "", null));

		Map<String, String> map = new HashMap<String, String>();
		Assert.assertSame(map, verifier.notEmpty(map));
		Assert.assertSame(map, verifier.notEmpty(map, ""));
		Assert.assertSame(map, verifier.notEmpty(map, "%s", "1"));
		Assert.assertSame(map, verifier.notEmptyCm(map, "", null));
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

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyMapWithFalse1() {
		verifier.notEmpty(new HashMap<String, String>());
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyMapWithFalse2() {
		verifier.notEmpty(new HashMap<String, String>(), "");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyMapWithFalse3() {
		verifier.notEmpty(new HashMap<String, String>(), "%s", "1");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNotEmptyCmMapWithFalse() {
		verifier.notEmptyCm(new HashMap<String, String>(), "", null);
	}

	// noNullElements*

	@Test
	public void testNoNullElementsWithTrue() {
		Object[] array = new Object[] { "" };
		Assert.assertSame(array, verifier.noNullElements(array));
		Assert.assertSame(array, verifier.noNullElements(array, ""));
		Assert.assertSame(array, verifier.noNullElements(array, "%s", "1"));
		Assert.assertSame(array, verifier.noNullElementsCm(array, "", null));

		List<String> list = Arrays.asList("");
		Assert.assertSame(list, verifier.noNullElements(list));
		Assert.assertSame(list, verifier.noNullElements(list, ""));
		Assert.assertSame(list, verifier.noNullElements(list, "%s", "1"));
		Assert.assertSame(list, verifier.noNullElementsCm(list, "", null));

		Map<String, String> map = new HashMap<String, String>();
		map.put("", "");
		Assert.assertSame(map, verifier.noNullKeys(map));
		Assert.assertSame(map, verifier.noNullKeys(map, ""));
		Assert.assertSame(map, verifier.noNullKeys(map, "%s", "1"));
		Assert.assertSame(map, verifier.noNullKeysCm(map, "", null));

		Assert.assertSame(map, verifier.noNullValues(map));
		Assert.assertSame(map, verifier.noNullValues(map, ""));
		Assert.assertSame(map, verifier.noNullValues(map, "%s", "1"));
		Assert.assertSame(map, verifier.noNullValuesCm(map, "", null));

		Assert.assertSame(map, verifier.noNullKeysValues(map));
		Assert.assertSame(map, verifier.noNullKeysValues(map, ""));
		Assert.assertSame(map, verifier.noNullKeysValues(map, "%s", "1"));
		Assert.assertSame(map, verifier.noNullKeysValuesCm(map, "", null));
	}

	@Test
	public void testNoNullElementsWithFalseDisabled() {
		verifier.setDisabled(true);
		Object[] array = new Object[] { null };
		Assert.assertSame(array, verifier.noNullElements(array));
		Assert.assertSame(array, verifier.noNullElements(array, ""));
		Assert.assertSame(array, verifier.noNullElements(array, "%s", "1"));
		Assert.assertSame(array, verifier.noNullElementsCm(array, "", null));

		List<Object> list = Arrays.asList((Object) null);
		Assert.assertSame(list, verifier.noNullElements(list));
		Assert.assertSame(list, verifier.noNullElements(list, ""));
		Assert.assertSame(list, verifier.noNullElements(list, "%s", "1"));
		Assert.assertSame(list, verifier.noNullElementsCm(list, "", null));

		Map<String, String> map = new HashMap<String, String>();
		map.put(null, null);
		Assert.assertSame(map, verifier.noNullKeys(map));
		Assert.assertSame(map, verifier.noNullKeys(map, ""));
		Assert.assertSame(map, verifier.noNullKeys(map, "%s", "1"));
		Assert.assertSame(map, verifier.noNullKeysCm(map, "", null));

		Assert.assertSame(map, verifier.noNullValues(map));
		Assert.assertSame(map, verifier.noNullValues(map, ""));
		Assert.assertSame(map, verifier.noNullValues(map, "%s", "1"));
		Assert.assertSame(map, verifier.noNullValuesCm(map, "", null));

		Assert.assertSame(map, verifier.noNullKeysValues(map));
		Assert.assertSame(map, verifier.noNullKeysValues(map, ""));
		Assert.assertSame(map, verifier.noNullKeysValues(map, "%s", "1"));
		Assert.assertSame(map, verifier.noNullKeysValuesCm(map, "", null));
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

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullKeysWithFalse1() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, "");
		verifier.noNullKeys(map);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullKeysWithFalse2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, "");
		verifier.noNullKeys(map, "");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullKeysWithFalse3() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, "");
		verifier.noNullKeys(map, "%s", 1);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullKeysCmWithFalse() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, "");
		verifier.noNullKeysCm(map, "", null);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullValuesWithFalse1() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", null);
		verifier.noNullValues(map);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullValuesWithFalse2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", null);
		verifier.noNullValues(map, "");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullValuesWithFalse3() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", null);
		verifier.noNullValues(map, "%s", 1);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullValuesCmWithFalse() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", null);
		verifier.noNullValuesCm(map, "", null);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullKeysValuesWithFalse1() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, null);
		verifier.noNullKeysValues(map);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullKeysValuesWithFalse2() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("", null);
		verifier.noNullKeysValues(map, "");
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullKeysValuesWithFalse3() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, "");
		verifier.noNullKeysValues(map, "%s", 1);
	}

	@Test(expected = BatchArgumentVerificationException.class)
	public void testNoNullKeysValuesCmWithFalse() {
		Map<String, String> map = new HashMap<String, String>();
		map.put(null, "");
		verifier.noNullKeysValuesCm(map, "", null);
	}
}
