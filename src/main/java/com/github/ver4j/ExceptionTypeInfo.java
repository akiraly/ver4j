package com.github.ver4j;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import javax.annotation.Nonnull;

@Nonnull
public class ExceptionTypeInfo<T extends GeneralVerificationException> {
	private final Class<T> type;

	private final Constructor<T> exceptionConstructor;

	private final Constructor<T> exceptionConstructorWithCause;

	public ExceptionTypeInfo(Class<T> exceptionType) {
		super();
		this.type = exceptionType;

		try {
			this.exceptionConstructor = exceptionType.getConstructor(
					String.class, String.class);

			this.exceptionConstructorWithCause = exceptionType.getConstructor(
					String.class, String.class, Throwable.class);
		} catch (NoSuchMethodException e) {
			throw new IllegalStateException(
					"No constructor with correct signature found for exception type: "
							+ exceptionType);
		}
	}

	public static <T extends GeneralVerificationException> ExceptionTypeInfo<T> of(
			Class<T> exceptionType) {
		return new ExceptionTypeInfo<T>(exceptionType);
	}

	public T create(String message, String category, Throwable cause)
			throws InstantiationException, IllegalAccessException,
			InvocationTargetException {
		T instance;
		if (cause == null)
			instance = exceptionConstructor.newInstance(message, category);
		else
			instance = exceptionConstructorWithCause.newInstance(message,
					category, cause);
		return instance;
	}

	public final Class<T> getType() {
		return type;
	}
}
