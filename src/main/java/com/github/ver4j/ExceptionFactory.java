package com.github.ver4j;

import java.lang.reflect.InvocationTargetException;
import java.util.Locale;

import javax.annotation.Nonnull;

public class ExceptionFactory<T extends RuntimeException & IVerificationException>
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

	public static final <T extends RuntimeException & IVerificationException> ExceptionFactory<T> of(
			String category, @Nonnull ExceptionTypeInfo<T> typeInfo,
			@Nonnull ExceptionMessageInfo messageInfo) {
		return new ExceptionFactory<T>(category, typeInfo, messageInfo);
	}

	public T newException() {
		return createException(null, null, new Object[] { null });
	}

	public T newException(Object errorMessage) {
		return createException(null, null, new Object[] { errorMessage });
	}

	public T newException(String errorMessageTemplate, Locale locale,
			Object[] errorMessageArgs) {
		return createException(locale, errorMessageTemplate, errorMessageArgs);
	}

	protected T createException(@Nonnull Locale locale, String messageTemplate,
			Object[] messageTemplateArgs) {
		String message = messageInfo.createMessage(locale, messageTemplate,
				messageTemplateArgs);
		try {
			return typeInfo.create(message, category);
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
