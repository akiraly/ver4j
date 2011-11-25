package com.github.ver4j;

import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

import com.github.ver4j.GroupVerifier.GroupInternal;

public class GroupVerifier extends AObjectVerifierAwareVerifier<GroupInternal> {
	private static final String FAILED_NOT_EMPTY_CAUSE = "the array/collection/iterable/map is null or emtpy";

	private static final String FAILED_NO_NULL_ELEMENTS_CAUSE = "the array/iterable contains null element";

	private static final String FAILED_NO_NULL_KEYS_CAUSE = "the map contains null key";

	private static final String FAILED_NO_NULL_VALUES_CAUSE = "the map contains null value";

	private static final String FAILED_NO_NULL_KEYS_VALUES_CAUSE = "the map contains null key/value";

	private final ExceptionFactory<?> notEmptyExceptionFactory;

	private final ExceptionFactory<?> noNullElementsFactory;

	private final ExceptionFactory<?> noNullKeysFactory;

	private final ExceptionFactory<?> noNullValuesFactory;

	private final ExceptionFactory<?> noNullKeysValuesFactory;

	public interface GroupInternal {
	}

	private class NotVerifyingInternal implements GroupInternal {
	}

	private class VerifyingInternal implements GroupInternal {
	}

	public GroupVerifier(@Nonnull ObjectVerifier objectVerifier,
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

	@Override
	protected GroupInternal newNotVerifyingInternal() {
		return new NotVerifyingInternal();
	}

	@Override
	protected GroupInternal newVerifyingInternal() {
		return new VerifyingInternal();
	}

	@Nonnull
	public final <T> T[] notEmpty(@Nonnull T[] array) {
		object().notNull(array);
		if (isDisabled() || array.length > 0)
			return array;
		throw notEmptyExceptionFactory.newException();
	}

	@Nonnull
	public final <T> T[] notEmpty(@Nonnull T[] array, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(array, errorMessage, errorMessageArgs);
		if (isDisabled() || array.length > 0)
			return array;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	@Nonnull
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

	@Nonnull
	public final <T, C extends Collection<T>> C notEmpty(@Nonnull C collection) {
		object().notNull(collection);
		if (isDisabled() || !collection.isEmpty())
			return collection;
		throw notEmptyExceptionFactory.newException();
	}

	@Nonnull
	public final <T, C extends Collection<T>> C notEmpty(@Nonnull C collection,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(collection, errorMessage, errorMessageArgs);
		if (isDisabled() || !collection.isEmpty())
			return collection;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	@Nonnull
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

	@Nonnull
	public final <T, I extends Iterable<T>> I notEmpty(@Nonnull I iterable) {
		object().notNull(iterable);
		if (isDisabled() || iterable.iterator().hasNext())
			return iterable;
		throw notEmptyExceptionFactory.newException();
	}

	@Nonnull
	public final <T, I extends Iterable<T>> I notEmpty(@Nonnull I iterable,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(iterable, errorMessage, errorMessageArgs);
		if (isDisabled() || iterable.iterator().hasNext())
			return iterable;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	@Nonnull
	public final <T, I extends Iterable<T>> I notEmptyCm(@Nonnull I iterable,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(iterable, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || iterable.iterator().hasNext())
			return iterable;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M notEmpty(@Nonnull M map) {
		object().notNull(map);
		if (isDisabled() || !map.isEmpty())
			return map;
		throw notEmptyExceptionFactory.newException();
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M notEmpty(@Nonnull M map,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || !map.isEmpty())
			return map;
		throw notEmptyExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M notEmptyCm(@Nonnull M map,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || !map.isEmpty())
			return map;
		throw notEmptyExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	@Nonnull
	private final Map<Object, Object> newContext(@Nonnull Map<?, ?> map) {
		Map<Object, Object> context = object().newContextMap();
		context.put("map", map);
		return context;
	}

	@Nonnull
	private final Map<Object, Object> newContext(@Nonnull Iterable<?> iterable) {
		Map<Object, Object> context = object().newContextMap();
		context.put("iterable", iterable);
		return context;
	}

	@Nonnull
	private final Map<Object, Object> newContext(@Nonnull Object[] array) {
		Map<Object, Object> context = object().newContextMap();
		context.put("array", Arrays.toString(array));
		return context;
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullKeys(@Nonnull M map) {
		object().notNull(map);
		if (isDisabled() || !hasNull(map.keySet()))
			return map;
		throw noNullKeysFactory.newException(newContext(map), (Throwable) null);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullKeys(@Nonnull M map,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(map.keySet()))
			return map;
		throw noNullKeysFactory.newException(errorMessage, errorMessageArgs,
				newContext(map), null);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullKeysCm(@Nonnull M map,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || !hasNull(map.keySet()))
			return map;
		throw noNullKeysFactory.newExceptionCm(errorMessageTemplate, locale,
				errorMessageArgs, newContext(map), null);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullValues(@Nonnull M map) {
		object().notNull(map);
		if (isDisabled() || !hasNull(map.values()))
			return map;
		throw noNullValuesFactory.newException(newContext(map),
				(Throwable) null);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullValues(@Nonnull M map,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(map.values()))
			return map;
		throw noNullValuesFactory.newException(errorMessage, errorMessageArgs,
				newContext(map), null);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullValuesCm(@Nonnull M map,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || !hasNull(map.values()))
			return map;
		throw noNullValuesFactory.newExceptionCm(errorMessageTemplate, locale,
				errorMessageArgs, newContext(map), null);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullKeysValues(@Nonnull M map) {
		object().notNull(map);
		if (isDisabled() || !hasNullKeyOrValue(map.entrySet()))
			return map;
		throw noNullKeysValuesFactory.newException(newContext(map),
				(Throwable) null);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullKeysValues(@Nonnull M map,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(map, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNullKeyOrValue(map.entrySet()))
			return map;
		throw noNullKeysValuesFactory.newException(errorMessage,
				errorMessageArgs, newContext(map), null);
	}

	@Nonnull
	public final <K, V, M extends Map<K, V>> M noNullKeysValuesCm(
			@Nonnull M map, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(map, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || !hasNullKeyOrValue(map.entrySet()))
			return map;
		throw noNullKeysValuesFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs, newContext(map), null);
	}

	private <K, V> boolean hasNullKeyOrValue(Iterable<Map.Entry<K, V>> iterable) {
		for (Map.Entry<K, V> e : iterable)
			if (e == null || e.getKey() == null || e.getValue() == null)
				return true;
		return false;
	}

	@Nonnull
	public final <T, I extends Iterable<T>> I noNullElements(@Nonnull I iterable) {
		object().notNull(iterable);
		if (isDisabled() || !hasNull(iterable))
			return iterable;
		throw noNullElementsFactory.newException(newContext(iterable),
				(Throwable) null);
	}

	@Nonnull
	public final <T, I extends Iterable<T>> I noNullElements(
			@Nonnull I iterable, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(iterable, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(iterable))
			return iterable;
		throw noNullElementsFactory.newException(errorMessage,
				errorMessageArgs, newContext(iterable), null);
	}

	@Nonnull
	public final <T, I extends Iterable<T>> I noNullElementsCm(
			@Nonnull I iterable, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(iterable, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || !hasNull(iterable))
			return iterable;
		throw noNullElementsFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs, newContext(iterable), null);
	}

	private boolean hasNull(Iterable<?> iterable) {
		for (Object el : iterable)
			if (el == null)
				return true;
		return false;
	}

	@Nonnull
	public final <T> T[] noNullElements(@Nonnull T[] array) {
		object().notNull(array);
		if (isDisabled() || !hasNull(array))
			return array;
		throw noNullElementsFactory.newException(newContext(array),
				(Throwable) null);
	}

	@Nonnull
	public final <T> T[] noNullElements(@Nonnull T[] array,
			Object errorMessage, Object... errorMessageArgs) {
		object().notNull(array, errorMessage, errorMessageArgs);
		if (isDisabled() || !hasNull(array))
			return array;
		throw noNullElementsFactory.newException(errorMessage,
				errorMessageArgs, newContext(array), null);
	}

	@Nonnull
	public final <T> T[] noNullElementsCm(@Nonnull T[] array,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(array, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || !hasNull(array))
			return array;
		throw noNullElementsFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs, newContext(array), null);
	}

	private boolean hasNull(Object[] array) {
		for (Object el : array)
			if (el == null)
				return true;
		return false;
	}
}
