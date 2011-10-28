package com.github.ver4j;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

public class BatchVerifier extends AObjectVerifierAwareVerifier {
	private static final String FAILED_NOT_EMPTY_CAUSE = "it is a null or emtpy array/collection/map";

	private static final String FAILED_NO_NULL_ELEMENTS_CAUSE = "it is a null element containing array/iterable";

	private static final String FAILED_NO_NULL_KEYS_CAUSE = "it is a null key containing map";

	private static final String FAILED_NO_NULL_VALUES_CAUSE = "it is a null value containing map";

	private static final String FAILED_NO_NULL_KEYS_VALUES_CAUSE = "it is a null key/value containing map";

	private final ExceptionFactory<?> notEmptyExceptionFactory;

	private final ExceptionFactory<?> noNullElementsFactory;

	private final ExceptionFactory<?> noNullKeysFactory;

	private final ExceptionFactory<?> noNullValuesFactory;

	private final ExceptionFactory<?> noNullKeysValuesFactory;

	public BatchVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull ExceptionTypeInfo<?> batchExceptionTypeInfo) {
		super(objectVerifier);
		notEmptyExceptionFactory = exceptionFactoryOf(batchExceptionTypeInfo,
				FAILED_NOT_EMPTY_CAUSE);
		noNullElementsFactory = exceptionFactoryOf(batchExceptionTypeInfo,
				FAILED_NO_NULL_ELEMENTS_CAUSE);
		noNullKeysFactory = exceptionFactoryOf(batchExceptionTypeInfo,
				FAILED_NO_NULL_KEYS_CAUSE);
		noNullValuesFactory = exceptionFactoryOf(batchExceptionTypeInfo,
				FAILED_NO_NULL_VALUES_CAUSE);
		noNullKeysValuesFactory = exceptionFactoryOf(batchExceptionTypeInfo,
				FAILED_NO_NULL_KEYS_VALUES_CAUSE);
	}

	public final <T> T[] notEmpty(@Nonnull T[] array) {
		object().notNull(array);
		if (isDisabled() || array.length > 0)
			return array;
		throw notEmptyExceptionFactory.newException();
	}

	public final <T> T[] notEmpty(@Nonnull T[] array, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(array, errorMessage, errorMessageArgs);
		if (isDisabled() || array.length > 0)
			return array;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final <T> T[] notEmptyCm(@Nonnull T[] array,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(array, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || array.length > 0)
			return array;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final <T, C extends Collection<T>> C notEmpty(@Nonnull C collection) {
		object().notNull(collection);
		if (isDisabled() || !collection.isEmpty())
			return collection;
		throw notEmptyExceptionFactory.newException();
	}

	public final <T, C extends Collection<T>> C notEmpty(@Nonnull C collection,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(collection, errorMessage, errorMessageArgs);
		if (isDisabled() || !collection.isEmpty())
			return collection;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final <T, C extends Collection<T>> C notEmptyCm(
			@Nonnull C collection, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(collection, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || !collection.isEmpty())
			return collection;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final <K, V, M extends Map<K, V>> M notEmpty(@Nonnull M map) {
		object().notNull(map);
		if (isDisabled() || map.size() > 0)
			return map;
		throw notEmptyExceptionFactory.newException();
	}

	public final <K, V, M extends Map<K, V>> M notEmpty(@Nonnull M map,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || map.size() > 0)
			return map;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final <K, V, M extends Map<K, V>> M notEmptyCm(@Nonnull M map,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || map.size() > 0)
			return map;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final <K, V, M extends Map<K, V>> M noNullKeys(@Nonnull M map) {
		object().notNull(map);
		if (isDisabled() || !hasNull(map.keySet()))
			return map;
		throw noNullKeysFactory.newException();
	}

	public final <K, V, M extends Map<K, V>> M noNullKeys(@Nonnull M map,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(map.keySet()))
			return map;
		throw noNullKeysFactory.newException(errorMessage, errorMessageArgs);
	}

	public final <K, V, M extends Map<K, V>> M noNullKeysCm(@Nonnull M map,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || !hasNull(map.keySet()))
			return map;
		throw noNullKeysFactory.newExceptionCm(errorMessageTemplate, locale,
				errorMessageArgs);
	}

	public final <K, V, M extends Map<K, V>> M noNullValues(@Nonnull M map) {
		object().notNull(map);
		if (isDisabled() || !hasNull(map.values()))
			return map;
		throw noNullValuesFactory.newException();
	}

	public final <K, V, M extends Map<K, V>> M noNullValues(@Nonnull M map,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(map.values()))
			return map;
		throw noNullValuesFactory.newException(errorMessage, errorMessageArgs);
	}

	public final <K, V, M extends Map<K, V>> M noNullValuesCm(@Nonnull M map,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || !hasNull(map.values()))
			return map;
		throw noNullValuesFactory.newExceptionCm(errorMessageTemplate, locale,
				errorMessageArgs);
	}

	public final <K, V, M extends Map<K, V>> M noNullKeysValues(@Nonnull M map) {
		object().notNull(map);
		if (isDisabled() || !hasNullKeyOrValue(map.entrySet()))
			return map;
		throw noNullKeysValuesFactory.newException();
	}

	public final <K, V, M extends Map<K, V>> M noNullKeysValues(@Nonnull M map,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNullKeyOrValue(map.entrySet()))
			return map;
		throw noNullKeysValuesFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final <K, V, M extends Map<K, V>> M noNullKeysValuesCm(
			@Nonnull M map, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || !hasNullKeyOrValue(map.entrySet()))
			return map;
		throw noNullKeysValuesFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	private <K, V> boolean hasNullKeyOrValue(Iterable<Map.Entry<K, V>> iterable) {
		for (Map.Entry<K, V> e : iterable)
			if (e == null || e.getKey() == null || e.getValue() == null)
				return true;
		return false;
	}

	public final <T, I extends Iterable<T>> I noNullElements(@Nonnull I iterable) {
		object().notNull(iterable);
		if (isDisabled() || !hasNull(iterable))
			return iterable;
		throw noNullElementsFactory.newException();
	}

	public final <T, I extends Iterable<T>> I noNullElements(
			@Nonnull I iterable, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(iterable, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(iterable))
			return iterable;
		throw noNullElementsFactory
				.newException(errorMessage, errorMessageArgs);
	}

	public final <T, I extends Iterable<T>> I noNullElementsCm(
			@Nonnull I iterable, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(iterable, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || !hasNull(iterable))
			return iterable;
		throw noNullElementsFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	private boolean hasNull(Iterable<?> iterable) {
		for (Object el : iterable)
			if (el == null)
				return true;
		return false;
	}

	public final <T> T[] noNullElements(@Nonnull T[] array) {
		object().notNull(array);
		if (isDisabled() || !hasNull(array))
			return array;
		throw noNullElementsFactory.newException();
	}

	public final <T> T[] noNullElements(@Nonnull T[] array,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(array, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(array))
			return array;
		throw noNullElementsFactory
				.newException(errorMessage, errorMessageArgs);
	}

	public final <T> T[] noNullElementsCm(@Nonnull T[] array,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(array, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || !hasNull(array))
			return array;
		throw noNullElementsFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	private boolean hasNull(Object[] array) {
		for (Object el : array)
			if (el == null)
				return true;
		return false;
	}
}
