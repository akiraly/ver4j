package com.github.ver4j;

import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.Range;

import com.github.ver4j.OrderVerifier.OrderInternal;

public class OrderVerifier<C extends Comparable<C>> extends
		AObjectVerifierAwareVerifier<OrderInternal> {
	private static final String FAILED_IS_BEFORE_CAUSE = "the orderable object is not before the reference";

	private static final String FAILED_IS_AFTER_CAUSE = "the orderable object is not after the reference";

	private static final String FAILED_NOT_BEFORE_CAUSE = "the orderable object is before the reference";

	private static final String FAILED_NOT_AFTER_CAUSE = "the orderable object is after the reference";

	private static final String FAILED_EQUALS_CAUSE = "the orderable object is not equal to the reference";

	private static final String FAILED_NOT_EQUAL_CAUSE = "the orderable object is equal to the reference";

	private static final String FAILED_INCLUSIVE_BETWEEN_CAUSE = "the orderable object is not inclusive between the reference range";

	private static final String FAILED_EXCLUSIVE_BETWEEN_CAUSE = "the orderable object is not exclusive between the reference range";

	private static final String FAILED_IN_RANGE_CAUSE = "the orderable object is not contained by the reference range";

	private final ExceptionFactory<?> isBeforeExceptionFactory;

	private final ExceptionFactory<?> isAfterExceptionFactory;

	private final ExceptionFactory<?> notBeforeExceptionFactory;

	private final ExceptionFactory<?> notAfterExceptionFactory;

	private final ExceptionFactory<?> equalsExceptionFactory;

	private final ExceptionFactory<?> notEqualExceptionFactory;

	private final ExceptionFactory<?> inclusiveBetweenExceptionFactory;

	private final ExceptionFactory<?> exclusiveBetweenExceptionFactory;

	private final ExceptionFactory<?> inRangeExceptionFactory;

	public interface OrderInternal {
	}

	private class NotVerifyingInternal implements OrderInternal {
	}

	private class VerifyingInternal implements OrderInternal {
	}

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
		equalsExceptionFactory = exceptionFactoryOf(orderExceptionTypeInfo,
				FAILED_EQUALS_CAUSE);
		notEqualExceptionFactory = exceptionFactoryOf(orderExceptionTypeInfo,
				FAILED_NOT_EQUAL_CAUSE);
		inclusiveBetweenExceptionFactory = exceptionFactoryOf(
				orderExceptionTypeInfo, FAILED_INCLUSIVE_BETWEEN_CAUSE);
		exclusiveBetweenExceptionFactory = exceptionFactoryOf(
				orderExceptionTypeInfo, FAILED_EXCLUSIVE_BETWEEN_CAUSE);
		inRangeExceptionFactory = exceptionFactoryOf(orderExceptionTypeInfo,
				FAILED_IN_RANGE_CAUSE);
	}

	@Override
	protected OrderInternal newNotVerifyingInternal() {
		return new NotVerifyingInternal();
	}

	@Override
	protected OrderInternal newVerifyingInternal() {
		return new VerifyingInternal();
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
	public final <T extends C> T equals(@Nonnull T comparable,
			@Nonnull T reference) {
		object().notNull(comparable);
		object().notNull(reference);
		if (isDisabled() || comparable.compareTo(reference) == 0)
			return comparable;
		throw equalsExceptionFactory.newException(
				newComparableContext(comparable, reference), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T equals(@Nonnull T comparable,
			@Nonnull T reference, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(reference, errorMessage, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) == 0)
			return comparable;
		throw equalsExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, reference),
				null);
	}

	@Nonnull
	public final <T extends C> T equalsCm(@Nonnull T comparable,
			@Nonnull T reference, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(reference, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) == 0)
			return comparable;
		throw equalsExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs,
				newComparableContext(comparable, reference), null);
	}

	@Nonnull
	public final <T extends C> T notEqual(@Nonnull T comparable,
			@Nonnull T reference) {
		object().notNull(comparable);
		object().notNull(reference);
		if (isDisabled() || comparable.compareTo(reference) != 0)
			return comparable;
		throw notEqualExceptionFactory.newException(
				newComparableContext(comparable, reference), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T notEqual(@Nonnull T comparable,
			@Nonnull T reference, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(reference, errorMessage, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) != 0)
			return comparable;
		throw notEqualExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, reference),
				null);
	}

	@Nonnull
	public final <T extends C> T notEqualCm(@Nonnull T comparable,
			@Nonnull T reference, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(reference, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || comparable.compareTo(reference) != 0)
			return comparable;
		throw notEqualExceptionFactory.newExceptionCm(errorMessageTemplate,
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

	@Nonnull
	public final <T extends C> T inclusiveBetween(@Nonnull T comparable,
			@Nonnull T min, @Nonnull T max) {
		object().notNull(comparable);
		object().notNull(min);
		object().notNull(max);
		if (isDisabled() || comparable.compareTo(min) >= 0
				&& comparable.compareTo(max) <= 0)
			return comparable;
		throw inclusiveBetweenExceptionFactory.newException(
				newComparableContext(comparable, min, max), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T inclusiveBetween(@Nonnull T comparable,
			@Nonnull T min, @Nonnull T max, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(min, errorMessage, errorMessageArgs);
		object().notNull(max, errorMessage, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(min) >= 0
				&& comparable.compareTo(max) <= 0)
			return comparable;
		throw inclusiveBetweenExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, min, max),
				(Throwable) null);
	}

	@Nonnull
	public final <T extends C> T inclusiveBetweenCm(@Nonnull T comparable,
			@Nonnull T min, @Nonnull T max, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(min, errorMessageTemplate, locale, errorMessageArgs);
		object().notNullCm(max, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(min) >= 0
				&& comparable.compareTo(max) <= 0)
			return comparable;
		throw inclusiveBetweenExceptionFactory.newExceptionCm(
				errorMessageTemplate, locale, errorMessageArgs,
				newComparableContext(comparable, min, max), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T exclusiveBetween(@Nonnull T comparable,
			@Nonnull T min, @Nonnull T max) {
		object().notNull(comparable);
		object().notNull(min);
		object().notNull(max);
		if (isDisabled() || comparable.compareTo(min) > 0
				&& comparable.compareTo(max) < 0)
			return comparable;
		throw exclusiveBetweenExceptionFactory.newException(
				newComparableContext(comparable, min, max), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T exclusiveBetween(@Nonnull T comparable,
			@Nonnull T min, @Nonnull T max, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(min, errorMessage, errorMessageArgs);
		object().notNull(max, errorMessage, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(min) > 0
				&& comparable.compareTo(max) < 0)
			return comparable;
		throw exclusiveBetweenExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, min, max),
				(Throwable) null);
	}

	@Nonnull
	public final <T extends C> T exclusiveBetweenCm(@Nonnull T comparable,
			@Nonnull T min, @Nonnull T max, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(min, errorMessageTemplate, locale, errorMessageArgs);
		object().notNullCm(max, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || comparable.compareTo(min) > 0
				&& comparable.compareTo(max) < 0)
			return comparable;
		throw exclusiveBetweenExceptionFactory.newExceptionCm(
				errorMessageTemplate, locale, errorMessageArgs,
				newComparableContext(comparable, min, max), (Throwable) null);
	}

	@Nonnull
	private final <T extends C> Map<Object, Object> newComparableContext(
			@Nonnull T comparable, @Nonnull T min, @Nonnull T max) {
		Map<Object, Object> context = object().newContextMap();
		context.put("tested object", comparable);
		context.put("reference minimum", min);
		context.put("reference maximum", max);
		return context;
	}

	@Nonnull
	public final <T extends C> T inRange(@Nonnull T comparable,
			@Nonnull Range<T> range) {
		object().notNull(comparable);
		object().notNull(range);
		if (isDisabled() || range.contains(comparable))
			return comparable;
		throw inRangeExceptionFactory.newException(
				newComparableContext(comparable, range), (Throwable) null);
	}

	@Nonnull
	public final <T extends C> T inRange(@Nonnull T comparable,
			@Nonnull Range<T> range, Object errorMessage,
			Object... errorMessageArgs) {
		object().notNull(comparable, errorMessage, errorMessageArgs);
		object().notNull(range, errorMessage, errorMessageArgs);
		if (isDisabled() || range.contains(comparable))
			return comparable;
		throw inRangeExceptionFactory.newException(errorMessage,
				errorMessageArgs, newComparableContext(comparable, range),
				(Throwable) null);
	}

	@Nonnull
	public final <T extends C> T inRangeCm(@Nonnull T comparable,
			@Nonnull Range<T> range, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		object().notNullCm(comparable, errorMessageTemplate, locale,
				errorMessageArgs);
		object().notNullCm(range, errorMessageTemplate, locale,
				errorMessageArgs);
		if (isDisabled() || range.contains(comparable))
			return comparable;
		throw inRangeExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs,
				newComparableContext(comparable, range), (Throwable) null);
	}

	@Nonnull
	private final <T extends C> Map<Object, Object> newComparableContext(
			@Nonnull T comparable, @Nonnull Range<T> range) {
		Map<Object, Object> context = object().newContextMap();
		context.put("tested object", comparable);
		context.put("reference range", range);
		return context;
	}
}
