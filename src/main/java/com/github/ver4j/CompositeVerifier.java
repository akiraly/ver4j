package com.github.ver4j;

import javax.annotation.Nonnull;

public class CompositeVerifier extends ObjectVerifier {
	@Nonnull
	public final TextVerifier<CharSequence> text;

	@Nonnull
	public final NumberVerifier<?> number;

	@Nonnull
	public final GroupVerifier group;

	@Nonnull
	public final FileVerifier file;

	@Nonnull
	public final OrderVerifier<?> order;

	@Nonnull
	public final TimeVerifier<?> time;

	public CompositeVerifier(String category,
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> classCastExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> textExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> batchExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> orderExceptionTypeInfo) {
		this(category, defaultExceptionMessageInfo, generalExceptionTypeInfo,
				classCastExceptionTypeInfo, nullExceptionTypeInfo,
				textExceptionTypeInfo, batchExceptionTypeInfo,
				orderExceptionTypeInfo, null, null, null, null, null, null);
	}

	public CompositeVerifier(String category,
			@Nonnull ExceptionMessageInfo defaultExceptionMessageInfo,
			@Nonnull ExceptionTypeInfo<?> generalExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> classCastExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> nullExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> textExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> batchExceptionTypeInfo,
			@Nonnull ExceptionTypeInfo<?> orderExceptionTypeInfo,
			TextVerifier<CharSequence> text, NumberVerifier<?> number,
			GroupVerifier batch, FileVerifier file, OrderVerifier<?> order,
			TimeVerifier<?> time) {
		super(category, defaultExceptionMessageInfo, generalExceptionTypeInfo,
				classCastExceptionTypeInfo, nullExceptionTypeInfo);

		this.text = text != null ? text : new TextVerifier<CharSequence>(this,
				textExceptionTypeInfo);

		if (order != null)
			this.order = order;
		else {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			OrderVerifier verifier = new OrderVerifier(this,
					orderExceptionTypeInfo);
			this.order = verifier;
		}

		if (number != null)
			this.number = number;
		else {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			NumberVerifier verifier = new NumberVerifier(this, order);
			this.number = verifier;
		}

		if (time != null)
			this.time = time;
		else {
			@SuppressWarnings({ "rawtypes", "unchecked" })
			TimeVerifier verifier = new TimeVerifier(this, order);
			this.time = verifier;
		}

		group = batch != null ? batch : new GroupVerifier(this,
				batchExceptionTypeInfo);

		this.file = file != null ? file : new FileVerifier(this);
	}

	@Override
	public void setDisabled(boolean disabled) {
		super.setDisabled(disabled);
		text.setDisabled(disabled);
		number.setDisabled(disabled);
		group.setDisabled(disabled);
		file.setDisabled(disabled);
		order.setDisabled(disabled);
	}
}
