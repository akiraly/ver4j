package com.github.ver4j;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

public class BatchVerifier extends AObjectVerifierAwareVerifier {
	private static final String FAILED_NOT_EMPTY_CAUSE = "it does not contain any elements";

	private final ExceptionFactory<?> notEmptyExceptionFactory;

	public BatchVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull ExceptionTypeInfo<?> batchExceptionTypeInfo) {
		super(objectVerifier);
		notEmptyExceptionFactory = exceptionFactoryOf(batchExceptionTypeInfo,
				FAILED_NOT_EMPTY_CAUSE);
	}

	public final void notEmpty(@Nonnull Object[] array) {
		object().notNull(array);
		if (isDisabled() || array.length > 0)
			return;
		throw notEmptyExceptionFactory.newException();
	}

	public final void notEmpty(@Nonnull Object[] array, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(array, errorMessage, errorMessageArgs);
		if (isDisabled() || array.length > 0)
			return;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void notEmptyCm(@Nonnull Object[] array,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(array, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || array.length > 0)
			return;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final void notEmpty(@Nonnull Collection<?> collection) {
		object().notNull(collection);
		if (isDisabled() || !collection.isEmpty())
			return;
		throw notEmptyExceptionFactory.newException();
	}

	public final void notEmpty(@Nonnull Collection<?> collection,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(collection, errorMessage, errorMessageArgs);
		if (isDisabled() || !collection.isEmpty())
			return;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void notEmptyCm(@Nonnull Collection<?> collection,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(collection, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || !collection.isEmpty())
			return;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final void notEmpty(@Nonnull Map<?, ?> map) {
		object().notNull(map);
		if (isDisabled() || map.size() > 0)
			return;
		throw notEmptyExceptionFactory.newException();
	}

	public final void notEmpty(@Nonnull Map<?, ?> map, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || map.size() > 0)
			return;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void notEmptyCm(@Nonnull Map<?, ?> map,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || map.size() > 0)
			return;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final void noNullElements(@Nonnull Iterable<?> iterable) {
		object().notNull(iterable);
		if (isDisabled() || !hasNull(iterable))
			return;
		throw notEmptyExceptionFactory.newException();
	}

	public final void noNullElements(@Nonnull Iterable<?> iterable,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(iterable, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(iterable))
			return;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void noNullElementsCm(@Nonnull Iterable<?> iterable,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(iterable, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || !hasNull(iterable))
			return;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	private boolean hasNull(Iterable<?> iterable) {
		for (Object el : iterable)
			if (el == null)
				return true;
		return false;
	}

	public final void noNullElements(@Nonnull Object[] array) {
		object().notNull(array);
		if (isDisabled() || !hasNull(array))
			return;
		throw notEmptyExceptionFactory.newException();
	}

	public final void noNullElements(@Nonnull Object[] array,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(array, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(array))
			return;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void noNullElementsCm(@Nonnull Object[] array,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(array, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || !hasNull(array))
			return;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	private boolean hasNull(Object[] array) {
		for (Object el : array)
			if (el == null)
				return true;
		return false;
	}
}
