package com.github.ver4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.annotation.Nonnull;

import junit.framework.Assert;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public abstract class AVerifierTestBase {

	protected static MethodInvocationParams of(Object returnValue,
			Object... args) {
		return new MethodInvocationParams(returnValue, args);
	}

	protected static MethodInvocationParams of(
			Class<? extends GeneralVerificationException> expectedExceptionType,
			Object returnValue, Object... args) {
		return new MethodInvocationParams(expectedExceptionType, returnValue,
				args);
	}

	protected static MethodInvocationParams of(
			Class<? extends GeneralVerificationException> expectedExceptionType,
			Class<? extends RuntimeException> expectedDisabledExceptionType,
			Object returnValue, Object... args) {
		return new MethodInvocationParams(expectedExceptionType,
				expectedDisabledExceptionType, returnValue, args);
	}

	protected static MethodInvocationParams[] invocations(
			MethodInvocationParams... invocationParams) {
		return invocationParams;
	}

	private final IVerifier verifier;
	private final String methodName;
	private final Class<?>[] firstParameterTypes;
	private final List<MethodInvocationParams> passingInvocations = new ArrayList<MethodInvocationParams>();
	private final List<MethodInvocationParams> failingInvocations = new ArrayList<MethodInvocationParams>();

	private MethodInvocationParams current;

	public AVerifierTestBase(@Nonnull String methodName,
			@Nonnull Class<?>[] firstParameterTypes,
			@Nonnull MethodInvocationParams... invocationParams) {
		this.methodName = methodName;
		this.firstParameterTypes = firstParameterTypes;
		verifier = newVerifier();

		for (MethodInvocationParams invParams : invocationParams) {
			Assert.assertEquals(firstParameterTypes.length,
					invParams.getArgs().length);
			if (invParams.shouldPass())
				passingInvocations.add(invParams);
			else
				failingInvocations.add(invParams);
		}
	}

	@Test
	public void testPassNoMessageParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : passingInvocations) {
			current = params;
			testPassingNoMessage();
		}
	}

	@Test
	public void testPassSingleMessageParam1() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingMessage(null, null);
		}
	}

	@Test
	public void testPassSingleMessageParam2() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingMessage("", null);
		}
	}

	@Test
	public void testPassMessageArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingMessage("%s", new Object[] { "1" });
		}
	}

	@Test
	public void testPassCmSimpleTemplateParam1() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingCm(null, null, null);
		}
	}

	@Test
	public void testPassCmSimpleTemplateParam2() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingCm("", null, null);
		}
	}

	@Test
	public void testPassCmTemplateArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingCm("%s", Locale.GERMAN, new Object[] { 3.14 });
		}
	}

	@Test
	public void testDisabledNoMessageParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testDisabledNoMessage();
		}
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingNoMessage();
		}
	}

	@Test
	public void testDisabledSingleMessageParam1() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testDisabledMessage(null, null);
		}
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingMessage(null, null);
		}
	}

	@Test
	public void testDisabledSingleMessageParam2() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testDisabledMessage("", null);
		}
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingMessage("", null);
		}
	}

	@Test
	public void testDisabledMessageArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testDisabledMessage("%s", new Object[] { "1" });
		}
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingMessage("%s", new Object[] { "1" });
		}
	}

	@Test
	public void testDisabledCmSimpleTemplateParam1()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		verifier.setDisabled(true);
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testDisabledCm(null, null, null);
		}
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingCm(null, null, null);
		}
	}

	@Test
	public void testDisabledCmSimpleTemplateParam2()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		verifier.setDisabled(true);
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testDisabledCm("", null, null);
		}
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingCm("", null, null);
		}
	}

	@Test
	public void testDisabledCmTemplateArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testDisabledCm("%s", Locale.GERMAN, new Object[] { 3.14 });
		}
		for (MethodInvocationParams params : passingInvocations) {
			current = params;

			testPassingCm("%s", Locale.GERMAN, new Object[] { 3.14 });
		}
	}

	@Test
	public void testFailingNoMessageParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testFailingNoMessage();
		}
	}

	@Test
	public void testFailingSingleMessageParam1() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testFailingMessage(null, null);
		}
	}

	@Test
	public void testFailingSingleMessageParam2() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testFailingMessage("", null);
		}
	}

	@Test
	public void testFailingMessageArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testFailingMessage("%s", new Object[] { "1" });
		}
	}

	@Test
	public void testFailingCmSimpleTemplateParam1()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testFailingCm(null, null, null);
		}
	}

	@Test
	public void testFailingCmSimpleTemplateParam2()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testFailingCm("", null, null);
		}
	}

	@Test
	public void testFailingCmTemplateArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		for (MethodInvocationParams params : failingInvocations) {
			current = params;

			testFailingCm("%s", Locale.GERMAN, new Object[] { 3.14 });
		}
	}

	@Nonnull
	protected abstract IVerifier newVerifier();

	private void testPassingCm(String template, Locale locale, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getCmMethod();
		Object returnValue = invokeCm(method, template, locale, args);
		assertReturnValue(method, returnValue);
	}

	private void testDisabledCm(String template, Locale locale, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getCmMethod();
		Object returnValue;
		Object expectedDisabledExceptionType = current
				.getExpectedDisabledExceptionType();
		try {
			returnValue = invokeCm(method, template, locale, args);
		} catch (InvocationTargetException e) {
			if (expectedDisabledExceptionType == null
					|| !expectedDisabledExceptionType.equals(e.getCause()
							.getClass()))
				throw e;
			return;
		}
		Assert.assertNull("Verifier failed at failing.",
				expectedDisabledExceptionType);
		assertReturnValue(method, returnValue);
	}

	private void testFailingCm(String template, Locale locale, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getCmMethod();
		boolean failedCorrectly = false;
		try {
			invokeCm(method, template, locale, args);
		} catch (InvocationTargetException e) {
			Object expectedExceptionType = current.getExpectedExceptionType();
			if (!expectedExceptionType.equals(e.getCause().getClass()))
				throw e;
			failedCorrectly = true;
		}
		Assert.assertTrue("Verifier failed at failing.", failedCorrectly);
	}

	private void testPassingMessage(String message, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getMessageMethod();
		Object returnValue = invokeMessage(method, message, args);
		assertReturnValue(method, returnValue);
	}

	private void testDisabledMessage(String message, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getMessageMethod();
		Object returnValue;
		Object expectedDisabledExceptionType = current
				.getExpectedDisabledExceptionType();
		try {
			returnValue = invokeMessage(method, message, args);
		} catch (InvocationTargetException e) {
			if (expectedDisabledExceptionType == null
					|| !expectedDisabledExceptionType.equals(e.getCause()
							.getClass()))
				throw e;
			return;
		}
		Assert.assertNull("Verifier failed at failing.",
				expectedDisabledExceptionType);
		assertReturnValue(method, returnValue);
	}

	private void testFailingMessage(String message, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getMessageMethod();
		boolean failedCorrectly = false;
		try {
			invokeMessage(method, message, args);
		} catch (InvocationTargetException e) {
			Object expectedExceptionType = current.getExpectedExceptionType();
			if (!expectedExceptionType.equals(e.getCause().getClass()))
				throw e;
			failedCorrectly = true;
		}
		Assert.assertTrue("Verifier failed at failing.", failedCorrectly);
	}

	private void testPassingNoMessage() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Method method = getNoMessageMethod();
		Object returnValue = invokeNoMessage(method);
		assertReturnValue(method, returnValue);
	}

	private void testDisabledNoMessage() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Method method = getNoMessageMethod();
		Object returnValue;
		Object expectedDisabledExceptionType = current
				.getExpectedDisabledExceptionType();
		try {
			returnValue = invokeNoMessage(method);
		} catch (InvocationTargetException e) {
			if (expectedDisabledExceptionType == null
					|| !expectedDisabledExceptionType.equals(e.getCause()
							.getClass()))
				throw e;
			return;
		}
		Assert.assertNull("Verifier failed at failing.",
				expectedDisabledExceptionType);
		assertReturnValue(method, returnValue);
	}

	private void testFailingNoMessage() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Method method = getNoMessageMethod();
		boolean failedCorrectly = false;
		try {
			invokeNoMessage(method);
		} catch (InvocationTargetException e) {
			Object expectedExceptionType = current.getExpectedExceptionType();
			if (!expectedExceptionType.equals(e.getCause().getClass()))
				throw e;
			failedCorrectly = true;
		}
		Assert.assertTrue("Verifier failed at failing.", failedCorrectly);
	}

	private Object invokeCm(Method method, String template, Locale locale,
			Object[] args) throws IllegalAccessException,
			InvocationTargetException {
		return method.invoke(verifier,
				ArrayUtils.addAll(current.getArgs(), template, locale, args));
	}

	private Object invokeMessage(Method method, String message, Object[] args)
			throws IllegalAccessException, InvocationTargetException {
		return method.invoke(verifier,
				ArrayUtils.addAll(current.getArgs(), message, args));
	}

	private Object invokeNoMessage(Method method)
			throws IllegalAccessException, InvocationTargetException {
		return method.invoke(verifier, current.getArgs());
	}

	private void assertReturnValue(Method method, Object returnValue) {
		if (method.getReturnType().isPrimitive())
			Assert.assertEquals(current.getReturnValue(), returnValue);
		else
			Assert.assertSame(current.getReturnValue(), returnValue);
	}

	private Method getNoMessageMethod() throws NoSuchMethodException {
		return verifier.getClass().getMethod(methodName, firstParameterTypes);
	}

	private Method getMessageMethod() throws NoSuchMethodException {
		return verifier.getClass().getMethod(
				methodName,
				ArrayUtils.addAll(firstParameterTypes, Object.class,
						ArrayUtils.EMPTY_OBJECT_ARRAY.getClass()));
	}

	private Method getCmMethod() throws NoSuchMethodException {
		return verifier.getClass()
				.getMethod(
						methodName + "Cm",
						ArrayUtils.addAll(firstParameterTypes, String.class,
								Locale.class,
								ArrayUtils.EMPTY_OBJECT_ARRAY.getClass()));
	}
}
