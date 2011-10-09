package com.github.ver4j;

import javax.annotation.Nonnull;

public class CompositeVerifier extends ObjectVerifier {
	@Nonnull
	public final TextVerifier text;

	@Nonnull
	public final NumberVerifier number;

	@Nonnull
	public final CollectionAndArrayVerifier collection;

	@Nonnull
	public final FileVerifier file;

	public CompositeVerifier(String category,
			@Nonnull ExceptionFactory<?> generalExceptionFactory,
			@Nonnull ExceptionFactory<?> nullExceptionFactory) {
		this(category, generalExceptionFactory, nullExceptionFactory, null,
				null, null, null);
	}

	public CompositeVerifier(String category,
			@Nonnull ExceptionFactory<?> generalExceptionFactory,
			@Nonnull ExceptionFactory<?> nullExceptionFactory,
			TextVerifier text, NumberVerifier number,
			CollectionAndArrayVerifier collection, FileVerifier file) {
		super(category, generalExceptionFactory, nullExceptionFactory);

		this.text = text != null ? text : new TextVerifier(this);

		this.number = number != null ? number : new NumberVerifier(this);

		this.collection = collection != null ? collection
				: new CollectionAndArrayVerifier(this);

		this.file = file != null ? file : new FileVerifier(this);
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		text.setEnabled(enabled);
		number.setEnabled(enabled);
		collection.setEnabled(enabled);
		file.setEnabled(enabled);
	}
}
