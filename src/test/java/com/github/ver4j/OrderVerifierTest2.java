package com.github.ver4j;

import java.util.Arrays;
import java.util.List;

import org.junit.runners.Parameterized.Parameters;

import com.github.ver4j.arg.exception.ArgumentOrderVerificationException;
import com.github.ver4j.arg.exception.ArgumentTypeVerificationException;
import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.NullPointerArgumentVerificationException;

public class OrderVerifierTest2 extends AVerifierTestBase {

	@Parameters
	public static List<Object[]> constructorParameters() {
		new StringBuilder();

		return Arrays
				.asList(new Object[][] { //
						{
								"isBefore",
								new Class<?>[] { Comparable.class,
										Comparable.class }, //
								invocations(
										of(0, 0, 1), //
										of(ArgumentOrderVerificationException.class,
												1, 1, 0), //
										of(ArgumentOrderVerificationException.class,
												(Object) null, null, null)) }, //
						{
								"isAfter",
								new Class<?>[] { Comparable.class,
										Comparable.class }, //
								invocations(
										of(1, 1, 0), //
										of(ArgumentOrderVerificationException.class,
												0, 0, 1), //
										of(ArgumentOrderVerificationException.class,
												(Object) null, null, null)) }, //
						{
								"notBefore",
								new Class<?>[] { Comparable.class,
										Comparable.class }, //
								invocations(
										of(1, 1, 0), //
										of(0, 0, 0), //
										of(ArgumentOrderVerificationException.class,
												0, 0, 1), //
										of(ArgumentOrderVerificationException.class,
												(Object) null, null, null)) }, //
						{
								"notAfter",
								new Class<?>[] { Comparable.class,
										Comparable.class }, //
								invocations(
										of(0, 0, 1), //
										of(0, 0, 0), //
										of(ArgumentOrderVerificationException.class,
												1, 1, 0), //
										of(ArgumentOrderVerificationException.class,
												(Object) null, null, null)) } //
				});
	}

	public OrderVerifierTest2(String methodName,
			Class<?>[] firstParameterTypes,
			MethodInvocationParams... invocationParams) {
		super(methodName, firstParameterTypes, invocationParams);
	}

	@Override
	protected IVerifier newVerifier() {
		ObjectVerifier objectVerifier = new ObjectVerifier(null,
				new ExceptionMessageInfo("Test"),
				ExceptionTypeInfo.of(ArgumentVerificationException.class),
				ExceptionTypeInfo.of(ArgumentTypeVerificationException.class),
				ExceptionTypeInfo
						.of(NullPointerArgumentVerificationException.class));

		return new OrderVerifier<Integer>(objectVerifier,
				ExceptionTypeInfo.of(ArgumentOrderVerificationException.class));
	}
}
