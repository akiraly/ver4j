package com.github.ver4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nonnull;

@Nonnull
public class ExceptionInfo<T extends RuntimeException & IVerificationException> {
	private final Class<T> type;

	private final Constructor<T> exceptionConstructor;

	public ExceptionInfo(Class<T> exceptionType) {
		super();
		this.type = exceptionType;

		try {
			this.exceptionConstructor = exceptionType.getConstructor(
					String.class, String.class);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(
					"No constructor with correct signature found for exception type: "
							+ exceptionType);
		}
	}

	public static <T extends RuntimeException & IVerificationException> ExceptionInfo<T> of(
			Class<T> exceptionType) {
		return new ExceptionInfo<T>(exceptionType);
	}

	public T create(String message, String category)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException {
		return exceptionConstructor.newInstance(message, category);
	}

	public final Class<T> getType() {
		return type;
	}
}
