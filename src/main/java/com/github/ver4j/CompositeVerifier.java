package com.github.ver4j;

import javax.annotation.Nonnull;

public class CompositeVerifier extends ObjectVerifier {
	@Nonnull
	public final TextVerifier text;

	@Nonnull
	public final NumberVerifier<?> number;

	@Nonnull
	public final BatchVerifier batch;

	@Nonnull
	public final FileVerifier file;

	@Nonnull
	public final OrderVerifier<?> order;

	public CompositeVerifier(String category,
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> classCastExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> textExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> batchExceptionTypeInfo) {
		this(category, defaultExceptionMessageInfo, generalExceptionTypeInfo,
				classCastExceptionTypeInfo, nullExceptionTypeInfo,
				textExceptionTypeInfo, batchExceptionTypeInfo, null, null,
				null, null, null);
	}

	public CompositeVerifier(String category,
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> classCastExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> textExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> batchExceptionTypeInfo,
			TextVerifier text, NumberVerifier<?> number, BatchVerifier batch,
			FileVerifier file, OrderVerifier<?> order) {
		super(category, defaultExceptionMessageInfo, generalExceptionTypeInfo,
				classCastExceptionTypeInfo, nullExceptionTypeInfo);

		this.text = text != null ? text : new TextVerifier(this,
				textExceptionTypeInfo);

		if (number != null)
			this.number = number;
		else {
			@SuppressWarnings("rawtypes")
			NumberVerifier verifier = new NumberVerifier(this);
			this.number = verifier;
		}

		this.batch = batch != null ? batch : new BatchVerifier(this,
				batchExceptionTypeInfo);

		this.file = file != null ? file : new FileVerifier(this);

		if (order != null)
			this.order = order;
		else {
			@SuppressWarnings("rawtypes")
			OrderVerifier verifier = new OrderVerifier(this);
			this.order = verifier;
		}
	}

	@Override
	public void setDisabled(boolean disabled) {
		super.setDisabled(disabled);
		text.setDisabled(disabled);
		number.setDisabled(disabled);
		batch.setDisabled(disabled);
		file.setDisabled(disabled);
	}
}
