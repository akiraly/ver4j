package com.github.ver4j;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

public class OrderVerifier<C extends Comparable<C>> extends
		AObjectVerifierAwareVerifier {
	private static final String FAILED_IS_BEFORE_CAUSE = "the orderable object is not before the reference";

	private static final String FAILED_IS_AFTER_CAUSE = "the orderable object is not after the reference";

	private static final String FAILED_NOT_BEFORE_CAUSE = "the orderable object is before the reference";

	private static final String FAILED_NOT_AFTER_CAUSE = "the orderable object is after the reference";

	private final ExceptionFactory<?> isBeforeExceptionFactory;

	private final ExceptionFactory<?> isAfterExceptionFactory;

	private final ExceptionFactory<?> notBeforeExceptionFactory;

	private final ExceptionFactory<?> notAfterExceptionFactory;

	public OrderVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull ExceptionTypeInfo<?> orderExceptionTypeInfo) {
		super(objectVerifier);
		isBeforeExceptionFactory = exceptionFactoryOf(orderExceptionTypeInfo,
				FAILED_IS_BEFORE_CAUSE);
		isAfterExceptionFactory = exceptionFactoryOf(orderExceptionTypeInfo,
				FAILED_IS_AFTER_CAUSE);
		notBeforeExceptionFactory = exceptionFactoryOf(orderExceptionTypeInfo,
				FAILED_NOT_BEFORE_CAUSE);
		notAfterExceptionFactory = exceptionFactoryOf(orderExceptionTypeInfo,
				FAILED_NOT_AFTER_CAUSE);
	}

	@Nonnull
	public final <T extends C> T isBefore(@Nonnull T comparable,
			@Nonnull T reference) {
		object().notNull(comparable);
		object().notNull(reference);
		if (isDisabled() || comparable.compareTo(reference) < 0)
			return comparable;
		throw isBeforeExceptionFactory.newException(
				newComparableContext(comparable, reference), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T isBefore(@Nonnull T comparable,
			@Nonnull T reference, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(reference, errorMessage, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) < 0)
			return comparable;
		throw isBeforeExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, reference),
				null);
	}

	@Nonnull
	public final <T extends C> T isBeforeCm(@Nonnull T comparable,
			@Nonnull T reference, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(reference, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) < 0)
			return comparable;
		throw isBeforeExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs,
				newComparableContext(comparable, reference), null);
	}

	@Nonnull
	public final <T extends C> T isAfter(@Nonnull T comparable,
			@Nonnull T reference) {
		object().notNull(comparable);
		object().notNull(reference);
		if (isDisabled() || comparable.compareTo(reference) > 0)
			return comparable;
		throw isAfterExceptionFactory.newException(
				newComparableContext(comparable, reference), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T isAfter(@Nonnull T comparable,
			@Nonnull T reference, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(reference, errorMessage, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) > 0)
			return comparable;
		throw isAfterExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, reference),
				null);
	}

	@Nonnull
	public final <T extends C> T isAfterCm(@Nonnull T comparable,
			@Nonnull T reference, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(reference, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) > 0)
			return comparable;
		throw isAfterExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs,
				newComparableContext(comparable, reference), null);
	}

	@Nonnull
	public final <T extends C> T notBefore(@Nonnull T comparable,
			@Nonnull T reference) {
		object().notNull(comparable);
		object().notNull(reference);
		if (isDisabled() || comparable.compareTo(reference) >= 0)
			return comparable;
		throw notBeforeExceptionFactory.newException(
				newComparableContext(comparable, reference), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T notBefore(@Nonnull T comparable,
			@Nonnull T reference, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(reference, errorMessage, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) >= 0)
			return comparable;
		throw notBeforeExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, reference),
				null);
	}

	@Nonnull
	public final <T extends C> T notBeforeCm(@Nonnull T comparable,
			@Nonnull T reference, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(reference, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) >= 0)
			return comparable;
		throw notBeforeExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs,
				newComparableContext(comparable, reference), null);
	}

	@Nonnull
	public final <T extends C> T notAfter(@Nonnull T comparable,
			@Nonnull T reference) {
		object().notNull(comparable);
		object().notNull(reference);
		if (isDisabled() || comparable.compareTo(reference) <= 0)
			return comparable;
		throw notAfterExceptionFactory.newException(
				newComparableContext(comparable, reference), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T notAfter(@Nonnull T comparable,
			@Nonnull T reference, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(reference, errorMessage, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) <= 0)
			return comparable;
		throw notAfterExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, reference),
				null);
	}

	@Nonnull
	public final <T extends C> T notAfterCm(@Nonnull T comparable,
			@Nonnull T reference, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(reference, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) <= 0)
			return comparable;
		throw notAfterExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs,
				newComparableContext(comparable, reference), null);
	}

	@Nonnull
	private final <T extends C> Map<Object, Object> newComparableContext(
			@Nonnull T comparable, @Nonnull T reference) {
		Map<Object, Object> context = object().newContextMap();
		context.put("tested object", comparable);
		context.put("reference object", reference);
		return context;
	}

	/*
	 * inclusiveBetween, exclusiveBetween, in + range, greaterThan, lessThan,
	 * notGreaterThan, notLessThan, orderEquals, notOrderEquals
	 */
}
