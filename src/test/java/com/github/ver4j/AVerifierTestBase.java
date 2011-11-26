package com.github.ver4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Locale;

import javax.annotation.Nonnull;

import junit.framework.Assert;

import org.apache.commons.lang3.ArrayUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public abstract class AVerifierTestBase {

	private final IVerifier verifier;
	private final String methodName;
	private final Class<?>[] firstParameterTypes;
	private final Object[] passingParameters;
	private final Object passingReturnValue;
	private final Object[] failingParameters;
	private final Object failingReturnValue;
	private final Class<? extends GeneralVerificationException> expectedExceptionType;

	public AVerifierTestBase(
			@Nonnull String methodName,
			@Nonnull Class<?>[] firstParameterTypes,
			@Nonnull Object[] passingParameters,
			Object passingReturnValue,
			@Nonnull Object[] failingParameters,
			Object failingReturnValue,
			@Nonnull Class<? extends GeneralVerificationException> expectedExceptionType) {
		Assert.assertEquals(firstParameterTypes.length,
				passingParameters.length);
		Assert.assertEquals(firstParameterTypes.length,
				failingParameters.length);

		this.methodName = methodName;
		this.firstParameterTypes = firstParameterTypes;
		this.passingParameters = passingParameters;
		this.passingReturnValue = passingReturnValue;
		this.failingParameters = failingParameters;
		this.failingReturnValue = failingReturnValue;
		this.expectedExceptionType = expectedExceptionType;
		verifier = newVerifier();
	}

	@Test
	public void testPassNoMessageParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testPassingNoMessage();
	}

	@Test
	public void testPassSingleMessageParam1() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testPassingMessage(null, null);
	}

	@Test
	public void testPassSingleMessageParam2() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testPassingMessage("", null);
	}

	@Test
	public void testPassMessageArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testPassingMessage("%s", new Object[] { "1" });
	}

	@Test
	public void testPassCmSimpleTemplateParam1() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testPassingCm(null, null, null);
	}

	@Test
	public void testPassCmSimpleTemplateParam2() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testPassingCm("", null, null);
	}

	@Test
	public void testPassCmTemplateArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testPassingCm("%s", Locale.GERMAN, new Object[] { 3.14 });
	}

	@Test
	public void testDisabledNoMessageParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		testDisabledNoMessage();
		testPassingNoMessage();
	}

	@Test
	public void testDisabledSingleMessageParam1() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		testDisabledMessage(null, null);
		testPassingMessage(null, null);
	}

	@Test
	public void testDisabledSingleMessageParam2() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		testDisabledMessage("", null);
		testPassingMessage("", null);
	}

	@Test
	public void testDisabledMessageArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		testDisabledMessage("%s", new Object[] { "1" });
		testPassingMessage("%s", new Object[] { "1" });
	}

	@Test
	public void testDisabledCmSimpleTemplateParam1()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		verifier.setDisabled(true);
		testDisabledCm(null, null, null);
		testPassingCm(null, null, null);
	}

	@Test
	public void testDisabledCmSimpleTemplateParam2()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		verifier.setDisabled(true);
		testDisabledCm("", null, null);
		testPassingCm("", null, null);
	}

	@Test
	public void testDisabledCmTemplateArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		verifier.setDisabled(true);
		testDisabledCm("%s", Locale.GERMAN, new Object[] { 3.14 });
		testPassingCm("%s", Locale.GERMAN, new Object[] { 3.14 });
	}

	@Test
	public void testFailingNoMessageParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testFailingNoMessage();
	}

	@Test
	public void testFailingSingleMessageParam1() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testFailingMessage(null, null);
	}

	@Test
	public void testFailingSingleMessageParam2() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testFailingMessage("", null);
	}

	@Test
	public void testFailingMessageArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testFailingMessage("%s", new Object[] { "1" });
	}

	@Test
	public void testFailingCmSimpleTemplateParam1()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		testFailingCm(null, null, null);
	}

	@Test
	public void testFailingCmSimpleTemplateParam2()
			throws NoSuchMethodException, SecurityException,
			IllegalAccessException, InvocationTargetException {
		testFailingCm("", null, null);
	}

	@Test
	public void testFailingCmTemplateArgsParam() throws NoSuchMethodException,
			SecurityException, IllegalAccessException,
			InvocationTargetException {
		testFailingCm("%s", Locale.GERMAN, new Object[] { 3.14 });
	}

	@Nonnull
	protected abstract IVerifier newVerifier();

	private void testPassingCm(String template, Locale locale, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getCmMethod();
		Object returnValue = invokePassingCm(method, template, locale, args);
		assertPassingReturnValue(method, returnValue);
	}

	private void testDisabledCm(String template, Locale locale, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getCmMethod();
		Object returnValue = invokeFailingCm(method, template, locale, args);
		assertFailingReturnValue(method, returnValue);
	}

	private void testFailingCm(String template, Locale locale, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getCmMethod();
		boolean failedCorrectly = false;
		try {
			invokeFailingCm(method, template, locale, args);
		} catch (InvocationTargetException e) {
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
		Object returnValue = invokePassingMessage(method, message, args);
		assertPassingReturnValue(method, returnValue);
	}

	private void testDisabledMessage(String message, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getMessageMethod();
		Object returnValue = invokeFailingMessage(method, message, args);
		assertFailingReturnValue(method, returnValue);
	}

	private void testFailingMessage(String message, Object[] args)
			throws IllegalAccessException, InvocationTargetException,
			NoSuchMethodException {
		Method method = getMessageMethod();
		boolean failedCorrectly = false;
		try {
			invokeFailingMessage(method, message, args);
		} catch (InvocationTargetException e) {
			if (!expectedExceptionType.equals(e.getCause().getClass()))
				throw e;
			failedCorrectly = true;
		}
		Assert.assertTrue("Verifier failed at failing.", failedCorrectly);
	}

	private void testPassingNoMessage() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Method method = getNoMessageMethod();
		Object returnValue = invokePassingNoMessage(method);
		assertPassingReturnValue(method, returnValue);
	}

	private void testDisabledNoMessage() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Method method = getNoMessageMethod();
		Object returnValue = invokeFailingNoMessage(method);
		assertFailingReturnValue(method, returnValue);
	}

	private void testFailingNoMessage() throws IllegalAccessException,
			InvocationTargetException, NoSuchMethodException {
		Method method = getNoMessageMethod();
		boolean failedCorrectly = false;
		try {
			invokeFailingNoMessage(method);
		} catch (InvocationTargetException e) {
			if (!expectedExceptionType.equals(e.getCause().getClass()))
				throw e;
			failedCorrectly = true;
		}
		Assert.assertTrue("Verifier failed at failing.", failedCorrectly);
	}

	private Object invokePassingCm(Method method, String template,
			Locale locale, Object[] args) throws IllegalAccessException,
			InvocationTargetException {
		return invokeCm(method, passingParameters, template, locale, args);
	}

	private Object invokeFailingCm(Method method, String template,
			Locale locale, Object[] args) throws IllegalAccessException,
			InvocationTargetException {
		return invokeCm(method, failingParameters, template, locale, args);
	}

	private Object invokeCm(Method method, Object[] firstParameters,
			String template, Locale locale, Object[] args)
			throws IllegalAccessException, InvocationTargetException {
		return method.invoke(verifier,
				ArrayUtils.addAll(firstParameters, template, locale, args));
	}

	private Object invokePassingMessage(Method method, String message,
			Object[] args) throws IllegalAccessException,
			InvocationTargetException {
		return invokeMessage(method, passingParameters, message, args);
	}

	private Object invokeFailingMessage(Method method, String message,
			Object[] args) throws IllegalAccessException,
			InvocationTargetException {
		return invokeMessage(method, failingParameters, message, args);
	}

	private Object invokeMessage(Method method, Object[] firstParameters,
			String message, Object[] args) throws IllegalAccessException,
			InvocationTargetException {
		return method.invoke(verifier,
				ArrayUtils.addAll(firstParameters, message, args));
	}

	private Object invokePassingNoMessage(Method method)
			throws IllegalAccessException, InvocationTargetException {
		return invokeNoMessage(method, passingParameters);
	}

	private Object invokeFailingNoMessage(Method method)
			throws IllegalAccessException, InvocationTargetException {
		return invokeNoMessage(method, failingParameters);
	}

	private Object invokeNoMessage(Method method, Object[] firstParameters)
			throws IllegalAccessException, InvocationTargetException {
		return method.invoke(verifier, firstParameters);
	}

	private void assertPassingReturnValue(Method method, Object returnValue) {
		assertReturnValue(method, passingReturnValue, returnValue);
	}

	private void assertFailingReturnValue(Method method, Object returnValue) {
		assertReturnValue(method, failingReturnValue, returnValue);
	}

	private void assertReturnValue(Method method, Object expected,
			Object returnValue) {
		if (method.getReturnType().isPrimitive())
			Assert.assertEquals(expected, returnValue);
		else
			Assert.assertSame(expected, returnValue);
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
