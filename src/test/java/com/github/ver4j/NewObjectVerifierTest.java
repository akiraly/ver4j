package com.github.ver4j;

import java.util.Arrays;
import java.util.List;

import org.junit.runners.Parameterized.Parameters;

import com.github.ver4j.arg.exception.ArgumentTypeVerificationException;
import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.NullPointerArgumentVerificationException;

public class NewObjectVerifierTest extends AVerifierTestBase {

	@Parameters
	public static List<Object[]> constructorParameters() {
		Object test1 = new StringBuilder();

		return Arrays
				.asList(new Object[][] { //
						{
								"isTrue",
								new Class<?>[] { Boolean.TYPE }, //
								invocations(
										of(true, true), //
										of(ArgumentVerificationException.class,
												false, false)) }, //
						{
								"isFalse",
								new Class<?>[] { Boolean.TYPE }, //
								invocations(
										of(false, false), //
										of(ArgumentVerificationException.class,
												true, true)) }, //
						{
								"notNull",
								new Class<?>[] { Object.class }, //
								invocations(
										of(test1, test1), //
										of(NullPointerArgumentVerificationException.class,
												null, new Object[] { null })) }, //
						{ "isInstanceOf",
								new Class<?>[] { Object.class, Class.class }, //
								invocations(
										of(test1, test1, CharSequence.class), //
										of(ArgumentTypeVerificationException.class,
												ClassCastException.class,
												test1, test1,
												StringBuffer.class), //
										of(NullPointerArgumentVerificationException.class,
												(Object) null, null,
												StringBuffer.class)) } //
				});
	}

	public NewObjectVerifierTest(String methodName,
			Class<?>[] firstParameterTypes,
			MethodInvocationParams... invocationParams) {
		super(methodName, firstParameterTypes, invocationParams);
	}

	@Override
	protected IVerifier newVerifier() {
		return new ObjectVerifier(null, new ExceptionMessageInfo("Test"),
				ExceptionTypeInfo.of(ArgumentVerificationException.class),
				ExceptionTypeInfo.of(ArgumentTypeVerificationException.class),
				ExceptionTypeInfo
						.of(NullPointerArgumentVerificationException.class));
	}
}
