package com.github.ver4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Nonnull;

import org.apache.commons.lang3.exception.ContextedRuntimeException;

public class ExceptionFactory<T extends ContextedRuntimeException & IVerificationException>
		implements ICategorized {
	private final String category;
	private final ExceptionTypeInfo<T> typeInfo;
	private final ExceptionMessageInfo messageInfo;

	public ExceptionFactory(String category,
			@Nonnull ExceptionTypeInfo<T> typeInfo,
			@Nonnull ExceptionMessageInfo messageInfo) {
		this.category = category;
		this.typeInfo = typeInfo;
		this.messageInfo = messageInfo;
	}

	public static final <T extends ContextedRuntimeException & IVerificationException> ExceptionFactory<T> of(
			String category, @Nonnull ExceptionTypeInfo<T> typeInfo,
			@Nonnull ExceptionMessageInfo messageInfo) {
		return new ExceptionFactory<T>(category, typeInfo, messageInfo);
	}

	public final T newException() {
		return newException(null);
	}

	public final T newException(Map<?, ?> ctxtArgs) {
		return createException(null, null, new Object[] { null }, ctxtArgs);
	}

	public final T newException(Object errorMessage, Object[] errorMessageArgs) {
		return newException(errorMessage, errorMessageArgs, null);
	}

	public final T newException(Object errorMessage, Object[] errorMessageArgs,
			Map<?, ?> ctxtArgs) {
		Object arg;
		if (errorMessage != null && errorMessageArgs != null) {
			arg = messageInfo.createMessage(null, errorMessage.toString(),
					errorMessageArgs);
		} else {
			arg = errorMessage;
		}

		return createException(null, null, new Object[] { arg }, ctxtArgs);
	}

	public final T newExceptionCm(String errorMessageTemplate, Locale locale,
			Object[] errorMessageArgs) {
		return newExceptionCm(errorMessageTemplate, locale, errorMessageArgs,
				null);
	}

	public final T newExceptionCm(String errorMessageTemplate, Locale locale,
			Object[] errorMessageArgs, Map<?, ?> ctxtArgs) {
		return createException(locale, errorMessageTemplate, errorMessageArgs,
				ctxtArgs);
	}

	protected T createException(Locale locale, String messageTemplate,
			Object[] messageTemplateArgs, Map<?, ?> ctxtArgs) {
		String message = messageInfo.createMessage(locale, messageTemplate,
				messageTemplateArgs);
		try {
			T result = typeInfo.create(message, category);

			if (ctxtArgs != null) {
				for (Map.Entry<?, ?> entry : ctxtArgs.entrySet()) {
					result.addContextValue(String.valueOf(entry.getKey()),
							entry.getValue());
				}
			}

			return result;
		} catch (InstantiationException e) {
			throw failException(message, e);
		} catch (IllegalAccessException e) {
			throw failException(message, e);
		} catch (InvocationTargetException e) {
			throw failException(message, e);
		}
	}

	@Nonnull
	private IllegalStateException failException(String message,
			@Nonnull Exception cause) {
		return new IllegalStateException(
				String.format(
						messageInfo.getDefaultLocale(),
						"Exception while building verification exception with type (%s) and message (%s) for verifier with category (%s).",
						typeInfo.getType(), message, category), cause);
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Nonnull
	public ExceptionTypeInfo<T> getTypeInfo() {
		return typeInfo;
	}

	@Nonnull
	public ExceptionMessageInfo getMessageInfo() {
		return messageInfo;
	}
}
