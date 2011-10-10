package com.github.ver4j;

import java.util.Locale;

import javax.annotation.Nonnull;

public class ExceptionMessageInfo {
	@Nonnull
	private final String defaultMessageTemplate;

	@Nonnull
	private final Locale defaultLocale;

	public ExceptionMessageInfo(@Nonnull String prefix) {
		this(prefix + " (%s) failed verification because ", Locale.ENGLISH);
	}

	public ExceptionMessageInfo(@Nonnull String defaultMessageTemplate,
			@Nonnull Locale defaultLocale) {
		super();
		this.defaultMessageTemplate = defaultMessageTemplate;
		this.defaultLocale = defaultLocale;
	}

	@Nonnull
	public String createMessage(Locale locale, String messageTemplate,
			Object[] messageTemplateArgs) {
		return String.format(locale != null ? locale : defaultLocale,
				messageTemplate != null ? messageTemplate
						: defaultMessageTemplate, messageTemplateArgs);
	}

	@Nonnull
	public ExceptionMessageInfo appendCause(@Nonnull String cause) {
		return new ExceptionMessageInfo(defaultMessageTemplate + cause + '.',
				defaultLocale);
	}

	@Nonnull
	public String getDefaultMessageTemplate() {
		return defaultMessageTemplate;
	}

	@Nonnull
	public Locale getDefaultLocale() {
		return defaultLocale;
	}
}
