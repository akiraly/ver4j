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

		return Arrays.asList(new Object[][] { //
						{ "isTrue", new Class<?>[] { Boolean.TYPE }, //
								new Object[] { true }, true, //
								new Object[] { false }, false, //
								ArgumentVerificationException.class }, //
						{ "isFalse", new Class<?>[] { Boolean.TYPE }, //
								new Object[] { false }, false, //
								new Object[] { true }, true, //
								ArgumentVerificationException.class }, //
						{ "notNull", new Class<?>[] { Object.class }, //
								new Object[] { test1 }, test1, //
								new Object[] { null }, null, //
								NullPointerArgumentVerificationException.class } //
				});
	}

	public NewObjectVerifierTest(String methodName,
			Class<?>[] firstParameterTypes, Object[] passingParameters,
			Object passingReturnValue, Object[] failingParameters,
			Object failingReturnValue,
			Class<? extends GeneralVerificationException> expectedExceptionType) {
		super(methodName, firstParameterTypes, passingParameters,
				passingReturnValue, failingParameters, failingReturnValue,
				expectedExceptionType);
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
