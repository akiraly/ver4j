package com.github.ver4j;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

import com.github.ver4j.ObjectVerifier.ObjectInternal;

public class ObjectVerifier extends AInternalBasedVerifier<ObjectInternal> {
	private static final String FAILED_IS_TRUE_CAUSE = "the expression is false";

	private static final String FAILED_IS_FALSE_CAUSE = "the expression is true";

	private static final String FAILED_IS_SAME_AS_OR_SUPERTYPE_OF = "the type is not the same as/not a super type of the reference type";

	private static final String FAILED_IS_SAME_AS_OR_SUBTYPE_OF = "the type is not the same as/not a sub type of the reference type";

	private static final String FAILED_IS_INSTANCE_OF_CAUSE = "the object is not an instance of the reference class";

	private static final String FAILED_NOT_NULL_CAUSE = "the object is null";

	private final String category;

	private final ExceptionMessageInfo defaultExceptionMessageInfo;

	private final ExceptionFactory<?> isTrueExceptionFactory;

	private final ExceptionFactory<?> isFalseExceptionFactory;

	private final ExceptionFactory<?> isSameAsOrSuperTypeOfExceptionFactory;

	private final ExceptionFactory<?> isSameAsOrSubTypeOfExceptionFactory;

	private final ExceptionFactory<?> isInstanceOfExceptionFactory;

	private final ExceptionFactory<?> nullExceptionFactory;

	public interface ObjectInternal {
		boolean isTrue(boolean expression, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs);

		boolean isFalse(boolean expression, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs);

		@Nonnull
		<T> T notNull(@Nonnull T object, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs);

		@Nonnull
		<T> T isInstanceOf(@Nonnull Object object, @Nonnull Class<T> type,
				Locale locale, String errorMessageTemplate,
				Object errorMessage, Object[] errorMessageArgs);

		@Nonnull
		<C> Class<C> isSameAsOrSuperTypeOf(@Nonnull Class<C> type,
				@Nonnull Class<?> referenceType, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs);

		@Nonnull
		<C> Class<C> isSameAsOrSubTypeOf(@Nonnull Class<C> type,
				@Nonnull Class<?> referenceType, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs);
	}

	private class NotVerifyingInternal implements ObjectInternal {
		@Override
		public boolean isTrue(boolean expression, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {
			return expression;
		}

		@Override
		public boolean isFalse(boolean expression, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {
			return expression;
		}

		@Override
		public <T> T notNull(T object, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {
			return object;
		}

		@Override
		public <T> T isInstanceOf(Object object, Class<T> type, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {
			return type.cast(object);
		}

		@Override
		public <C> Class<C> isSameAsOrSuperTypeOf(Class<C> type,
				Class<?> referenceType, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {
			return type;
		}

		@Override
		public <C> Class<C> isSameAsOrSubTypeOf(Class<C> type,
				Class<?> referenceType, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {
			return type;
		}
	}

	private class VerifyingInternal implements ObjectInternal {
		@Override
		public final boolean isTrue(final boolean expression, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {

			return new AVerifierTask<Boolean>() {
				@Override
				protected Boolean result() {
					return expression;
				}

				@Override
				protected boolean isValid() {
					return expression;
				}
			}.verify(isTrueExceptionFactory, locale, errorMessageTemplate,
					errorMessage, errorMessageArgs);
		}

		@Override
		public boolean isFalse(final boolean expression, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {
			return new AVerifierTask<Boolean>() {
				@Override
				protected Boolean result() {
					return expression;
				}

				@Override
				protected boolean isValid() {
					return !expression;
				}
			}.verify(isFalseExceptionFactory, locale, errorMessageTemplate,
					errorMessage, errorMessageArgs);
		}

		@Override
		public <T> T notNull(final T object, Locale locale,
				String errorMessageTemplate, Object errorMessage,
				Object[] errorMessageArgs) {
			return new AVerifierTask<T>() {
				@Override
				protected T result() {
					return object;
				}

				@Override
				protected boolean isValid() {
					return object != null;
				}
			}.verify(nullExceptionFactory, locale, errorMessageTemplate,
					errorMessage, errorMessageArgs);
		}

		@Override
		public <T> T isInstanceOf(final Object object, final Class<T> type,
				final Locale locale, final String errorMessageTemplate,
				final Object errorMessage, final Object[] errorMessageArgs) {
			return new AVerifierTask<T>() {
				@Override
				protected T result() {
					return type.cast(object);
				}

				@Override
				protected boolean isValid() {
					notNull(object, locale, errorMessageTemplate, errorMessage,
							errorMessageArgs);
					notNull(type, locale, errorMessageTemplate, errorMessage,
							errorMessageArgs);
					return type.isInstance(object);
				}

				@Override
				protected Map<Object, Object> errorContext() {
					Map<Object, Object> context = newContextMap();
					context.put("tested object", object);
					context.put("reference class", type);
					return context;
				}
			}.verify(isInstanceOfExceptionFactory, locale,
					errorMessageTemplate, errorMessage, errorMessageArgs);
		}

		@Override
		public <C> Class<C> isSameAsOrSuperTypeOf(final Class<C> type,
				final Class<?> referenceType, final Locale locale,
				final String errorMessageTemplate, final Object errorMessage,
				final Object[] errorMessageArgs) {
			return new AVerifierTask<Class<C>>() {
				@Override
				protected Class<C> result() {
					return type;
				}

				@Override
				protected boolean isValid() {
					notNull(type, locale, errorMessageTemplate, errorMessage,
							errorMessageArgs);
					notNull(referenceType, locale, errorMessageTemplate,
							errorMessage, errorMessageArgs);
					return type.isAssignableFrom(referenceType);
				}

				@Override
				protected Map<Object, Object> errorContext() {
					Map<Object, Object> context = newContextMap();
					context.put("tested type", type);
					context.put("reference type", referenceType);
					return context;
				}
			}.verify(isSameAsOrSuperTypeOfExceptionFactory, locale,
					errorMessageTemplate, errorMessage, errorMessageArgs);
		}

		@Override
		public <C> Class<C> isSameAsOrSubTypeOf(final Class<C> type,
				final Class<?> referenceType, final Locale locale,
				final String errorMessageTemplate, final Object errorMessage,
				final Object[] errorMessageArgs) {
			return new AVerifierTask<Class<C>>() {
				@Override
				protected Class<C> result() {
					return type;
				}

				@Override
				protected boolean isValid() {
					notNull(type, locale, errorMessageTemplate, errorMessage,
							errorMessageArgs);
					notNull(referenceType, locale, errorMessageTemplate,
							errorMessage, errorMessageArgs);
					return referenceType.isAssignableFrom(type);
				}

				@Override
				protected Map<Object, Object> errorContext() {
					Map<Object, Object> context = newContextMap();
					context.put("tested type", type);
					context.put("reference type", referenceType);
					return context;
				}
			}.verify(isSameAsOrSubTypeOfExceptionFactory, locale,
					errorMessageTemplate, errorMessage, errorMessageArgs);
		}
	}

	public ObjectVerifier(String category,
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> classCastExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo) {
		this.category = category;
		this.defaultExceptionMessageInfo = defaultExceptionMessageInfo;
		isTrueExceptionFactory = exceptionFactoryOf(generalExceptionTypeInfo,
				defaultExceptionMessageInfo.appendCause(FAILED_IS_TRUE_CAUSE));
		isFalseExceptionFactory = exceptionFactoryOf(generalExceptionTypeInfo,
				defaultExceptionMessageInfo.appendCause(FAILED_IS_FALSE_CAUSE));
		nullExceptionFactory = exceptionFactoryOf(nullExceptionTypeInfo,
				defaultExceptionMessageInfo.appendCause(FAILED_NOT_NULL_CAUSE));
		isInstanceOfExceptionFactory = exceptionFactoryOf(
				classCastExceptionTypeInfo,
				defaultExceptionMessageInfo
						.appendCause(FAILED_IS_INSTANCE_OF_CAUSE));
		isSameAsOrSuperTypeOfExceptionFactory = exceptionFactoryOf(
				classCastExceptionTypeInfo,
				defaultExceptionMessageInfo
						.appendCause(FAILED_IS_SAME_AS_OR_SUPERTYPE_OF));
		isSameAsOrSubTypeOfExceptionFactory = exceptionFactoryOf(
				classCastExceptionTypeInfo,
				defaultExceptionMessageInfo
						.appendCause(FAILED_IS_SAME_AS_OR_SUBTYPE_OF));
	}

	@Override
	protected ObjectInternal newNotVerifyingInternal() {
		return new NotVerifyingInternal();
	}

	@Override
	protected ObjectInternal newVerifyingInternal() {
		return new VerifyingInternal();
	}

	public final boolean isTrue(boolean expression) {
		return internal().isTrue(expression, null, null, null, null);
	}

	public final boolean isTrue(boolean expression, Object errorMessage,
			Object... errorMessageArgs) {
		return internal().isTrue(expression, null, null, errorMessage,
				errorMessageArgs);
	}

	public final boolean isTrueCm(boolean expression,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		return internal().isTrue(expression, locale, errorMessageTemplate,
				null, errorMessageArgs);
	}

	public final boolean isFalse(boolean expression) {
		return internal().isFalse(expression, null, null, null, null);
	}

	public final boolean isFalse(boolean expression, Object errorMessage,
			Object... errorMessageArgs) {
		return internal().isFalse(expression, null, null, errorMessage,
				errorMessageArgs);
	}

	public final boolean isFalseCm(boolean expression,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		return internal().isFalse(expression, locale, errorMessageTemplate,
				null, errorMessageArgs);
	}

	@Nonnull
	public final <T> T notNull(@Nonnull T object) {
		return internal().notNull(object, null, null, null, null);
	}

	@Nonnull
	public final <T> T notNull(@Nonnull T object, Object errorMessage,
			Object... errorMessageArgs) {
		return internal().notNull(object, null, null, errorMessage,
				errorMessageArgs);
	}

	@Nonnull
	public final <T> T notNullCm(@Nonnull T object,
			String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		return internal().notNull(object, locale, errorMessageTemplate, null,
				errorMessageArgs);
	}

	@Nonnull
	public final <T> T isInstanceOf(@Nonnull Object object,
			@Nonnull Class<T> type) {
		return internal().isInstanceOf(object, type, null, null, null, null);
	}

	@Nonnull
	public final <T> T isInstanceOf(@Nonnull Object object,
			@Nonnull Class<T> type, Object errorMessage,
			Object... errorMessageArgs) {
		return internal().isInstanceOf(object, type, null, null, errorMessage,
				errorMessageArgs);
	}

	@Nonnull
	public final <T> T isInstanceOfCm(@Nonnull Object object,
			@Nonnull Class<T> type, String errorMessageTemplate, Locale locale,
			Object... errorMessageArgs) {
		return internal().isInstanceOf(object, type, locale,
				errorMessageTemplate, null, errorMessageArgs);
	}

	@Nonnull
	public final <C> Class<C> isSameAsOrSuperTypeOf(@Nonnull Class<C> type,
			@Nonnull Class<?> referenceType) {
		return internal().isSameAsOrSuperTypeOf(type, referenceType, null,
				null, null, null);
	}

	@Nonnull
	public final <C> Class<C> isSameAsOrSuperTypeOf(@Nonnull Class<C> type,
			@Nonnull Class<?> referenceType, Object errorMessage,
			Object... errorMessageArgs) {
		return internal().isSameAsOrSuperTypeOf(type, referenceType, null,
				null, errorMessage, errorMessageArgs);
	}

	@Nonnull
	public final <C> Class<C> isSameAsOrSuperTypeOfCm(@Nonnull Class<C> type,
			@Nonnull Class<?> referenceType, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		return internal().isSameAsOrSuperTypeOf(type, referenceType, locale,
				errorMessageTemplate, null, errorMessageArgs);
	}

	@Nonnull
	public final <C> Class<C> isSameAsOrSubTypeOf(@Nonnull Class<C> type,
			@Nonnull Class<?> referenceType) {
		return internal().isSameAsOrSubTypeOf(type, referenceType, null, null,
				null, null);
	}

	@Nonnull
	public final <C> Class<C> isSameAsOrSubTypeOf(@Nonnull Class<C> type,
			@Nonnull Class<?> referenceType, Object errorMessage,
			Object... errorMessageArgs) {
		return internal().isSameAsOrSubTypeOf(type, referenceType, null, null,
				errorMessage, errorMessageArgs);
	}

	@Nonnull
	public final <C> Class<C> isSameAsOrSubTypeOfCm(@Nonnull Class<C> type,
			@Nonnull Class<?> referenceType, String errorMessageTemplate,
			Locale locale, Object... errorMessageArgs) {
		return internal().isSameAsOrSubTypeOf(type, referenceType, locale,
				errorMessageTemplate, null, errorMessageArgs);
	}

	@Nonnull
	public ExceptionMessageInfo getDefaultExceptionMessageInfo() {
		return defaultExceptionMessageInfo;
	}

	@Override
	public final String getCategory() {
		return category;
	}

	@Nonnull
	protected Map<Object, Object> newContextMap() {
		return new LinkedHashMap<Object, Object>();
	}
}
