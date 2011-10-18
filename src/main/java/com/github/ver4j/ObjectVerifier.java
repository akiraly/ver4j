package com.github.ver4j;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

public class ObjectVerifier extends AVerifier {
	private static final String FAILED_IS_TRUE_CAUSE = "it is false";

	private static final String FAILED_IS_FALSE_CAUSE = "it is true";

	private static final String FAILED_IS_ASSIGNABLE_FROM_CAUSE = "the tested class can not be casted to the reference class";

	private static final String FAILED_IS_INSTANCE_OF_CAUSE = "the tested object is not an instance of the reference class";

	private static final String FAILED_NOT_NULL_CAUSE = "it is null";

	private final String category;

	private final ExceptionMessageInfo defaultExceptionMessageInfo;

	private final ExceptionFactory<?> isTrueExceptionFactory;

	private final ExceptionFactory<?> isFalseExceptionFactory;

	private final ExceptionFactory<?> isAssignableFromExceptionFactory;

	private final ExceptionFactory<?> isInstanceOfExceptionFactory;

	private final ExceptionFactory<?> nullExceptionFactory;

	public ObjectVerifier(String category,
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> classCastExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo) {
		this.category = category;
		this.defaultExceptionMessageInfo = defaultExceptionMessageInfo;
		isTrueExceptionFactory = ExceptionFactory.of(category,
				generalExceptionTypeInfo,
				defaultExceptionMessageInfo.appendCause(FAILED_IS_TRUE_CAUSE));
		isFalseExceptionFactory = ExceptionFactory.of(category,
				generalExceptionTypeInfo,
				defaultExceptionMessageInfo.appendCause(FAILED_IS_FALSE_CAUSE));
		isAssignableFromExceptionFactory = ExceptionFactory.of(category,
				classCastExceptionTypeInfo, defaultExceptionMessageInfo
						.appendCause(FAILED_IS_ASSIGNABLE_FROM_CAUSE));
		isInstanceOfExceptionFactory = ExceptionFactory.of(category,
				classCastExceptionTypeInfo, defaultExceptionMessageInfo
						.appendCause(FAILED_IS_INSTANCE_OF_CAUSE));
		nullExceptionFactory = ExceptionFactory.of(category,
				nullExceptionTypeInfo,
				defaultExceptionMessageInfo.appendCause(FAILED_NOT_NULL_CAUSE));
	}

	public final void isTrue(boolean expression) {
		if (isDisabled() || expression) {
			return;
		}
		throw isTrueExceptionFactory.newException();
	}

	public final void isTrue(boolean expression, Object errorMessage,
			Object... errorMessageArgs) {
		if (isDisabled() || expression) {
			return;
		}
		throw isTrueExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void isTrueCm(boolean expression, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		if (isDisabled() || expression) {
			return;
		}
		throw isTrueExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final void isFalse(boolean expression) {
		if (isDisabled() || !expression) {
			return;
		}
		throw isFalseExceptionFactory.newException();
	}

	public final void isFalse(boolean expression, Object errorMessage,
			Object... errorMessageArgs) {
		if (isDisabled() || !expression) {
			return;
		}
		throw isFalseExceptionFactory.newException(errorMessage,
				errorMessageArgs);
	}

	public final void isFalseCm(boolean expression,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		if (isDisabled() || !expression) {
			return;
		}
		throw isFalseExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs);
	}

	public final void isAssignableFrom(@Nonnull Class<?> superType,
			@Nonnull Class<?> type) {
		notNull(superType);
		notNull(type);
		if (isDisabled() || superType.isAssignableFrom(type)) {
			return;
		}
		throw isAssignableFromExceptionFactory
				.newException(newAssignableFromContext(superType, type));
	}

	public final void isAssignableFrom(@Nonnull Class<?> superType,
			@Nonnull Class<?> type, Object errorMessage,
			Object... errorMessageArgs) {
		notNull(superType, errorMessage, errorMessageArgs);
		notNull(type, errorMessage, errorMessageArgs);
		if (isDisabled() || superType.isAssignableFrom(type)) {
			return;
		}
		throw isAssignableFromExceptionFactory.newException(errorMessage,
				errorMessageArgs, newAssignableFromContext(superType, type));
	}

	public final void isAssignableFromCm(@Nonnull Class<?> superType,
			@Nonnull Class<?> type, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		notNull(superType, errorMessageTemplate, locale, errorMessageArgs);
		notNull(type, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || superType.isAssignableFrom(type)) {
			return;
		}
		throw isAssignableFromExceptionFactory.newExceptionCm(
				errorMessageTemplate, locale, errorMessageArgs,
				newAssignableFromContext(superType, type));
	}

	@Nonnull
	private final Map<Object, Object> newAssignableFromContext(
			@Nonnull Class<?> superType, @Nonnull Class<?> type) {
		Map<Object, Object> context = newContextMap();
		context.put("reference class", superType);
		context.put("tested class", type);
		return context;
	}

	@Nonnull
	public final <T> T isInstanceOf(@Nonnull Class<T> type, @Nonnull Object obj) {
		notNull(type);
		notNull(obj);
		if (isDisabled() || type.isInstance(obj)) {
			return type.cast(obj);
		}
		throw isInstanceOfExceptionFactory.newException(newInstanceOfContext(
				type, obj));
	}

	@Nonnull
	public final <T> T isInstanceOf(@Nonnull Class<T> type,
			@Nonnull Object obj, Object errorMessage,
			Object... errorMessageArgs) {
		notNull(type, errorMessage, errorMessageArgs);
		notNull(obj, errorMessage, errorMessageArgs);
		if (isDisabled() || type.isInstance(obj)) {
			return type.cast(obj);
		}
		throw isInstanceOfExceptionFactory.newException(errorMessage,
				errorMessageArgs, newInstanceOfContext(type, obj));
	}

	@Nonnull
	public final <T> T isInstanceOfCm(@Nonnull Class<T> type,
			@Nonnull Object obj, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		notNullCm(type, errorMessageTemplate, locale, errorMessageArgs);
		notNull(obj, errorMessageTemplate, locale, errorMessageArgs);
		if (isDisabled() || type.isInstance(obj)) {
			return type.cast(obj);
		}
		throw isInstanceOfExceptionFactory.newExceptionCm(errorMessageTemplate,
				locale, errorMessageArgs, newInstanceOfContext(type, obj));
	}

	@Nonnull
	private final Map<Object, Object> newInstanceOfContext(
			@Nonnull Class<?> type, @Nonnull Object obj) {
		Map<Object, Object> context = newContextMap();
		context.put("reference class", type);
		context.put("tested class", obj);
		return context;
	}

	@Nonnull
	public final <T> T notNull(@Nonnull T object) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw nullExceptionFactory.newException();
	}

	@Nonnull
	public final <T> T notNull(@Nonnull T object, Object errorMessage,
			Object... errorMessageArgs) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw nullExceptionFactory.newException(errorMessage, errorMessageArgs);
	}

	@Nonnull
	public final <T> T notNullCm(@Nonnull T object,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		if (isDisabled() || object != null) {
			return object;
		}
		throw nullExceptionFactory.newExceptionCm(errorMessageTemplate, locale,
				errorMessageArgs);
	}

	@Nonnull
	protected final Map<Object, Object> newContextMap() {
		return new LinkedHashMap<Object, Object>();
	}

	@Nonnull
	public ExceptionMessageInfo getDefaultExceptionMessageInfo() {
		return defaultExceptionMessageInfo;
	}

	@Override
	public final String getCategory() {
		return category;
	}
}
