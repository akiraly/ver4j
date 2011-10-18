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
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> classCastExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> textExceptionTypeInfo) {
		this(category, defaultExceptionMessageInfo, generalExceptionTypeInfo,
				classCastExceptionTypeInfo, nullExceptionTypeInfo,
				textExceptionTypeInfo, null, null, null, null);
	}

	public CompositeVerifier(String category,
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> classCastExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> textExceptionTypeInfo,
			TextVerifier text, NumberVerifier number,
			CollectionAndArrayVerifier collection, FileVerifier file) {
		super(category, defaultExceptionMessageInfo, generalExceptionTypeInfo,
				classCastExceptionTypeInfo, nullExceptionTypeInfo);

		this.text = text != null ? text : new TextVerifier(this,
				textExceptionTypeInfo);

		this.number = number != null ? number : new NumberVerifier(this);

		this.collection = collection != null ? collection
				: new CollectionAndArrayVerifier(this);

		this.file = file != null ? file : new FileVerifier(this);
	}

	@Override
	public void setDisabled(boolean disabled) {
		super.setDisabled(disabled);
		text.setDisabled(disabled);
		number.setDisabled(disabled);
		collection.setDisabled(disabled);
		file.setDisabled(disabled);
	}
}
