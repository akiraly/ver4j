package com.github.ver4j;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

public class OrderVerifier<C extends Comparable<C>> extends
		AObjectVerifierAwareVerifier {
	private static final String FAILED_IS_BEFORE_CAUSE = "the comparable object is not ordered before the reference";

	private final ExceptionFactory<?> isBeforeExceptionFactory;

	public OrderVerifier(@Nonnull ObjectVerifier objectVerifier,
			@Nonnull ExceptionTypeInfo<?> orderExceptionTypeInfo) {
		super(objectVerifier);
		isBeforeExceptionFactory = exceptionFactoryOf(orderExceptionTypeInfo,
				FAILED_IS_BEFORE_CAUSE);
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
