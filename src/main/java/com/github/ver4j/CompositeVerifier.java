package com.github.ver4j;

public class CompositeVerifier extends ObjectVerifier {
	public final TextVerifier text;

	public final NumberVerifier number;

	public final CollectionAndArrayVerifier collection;

	public final FileVerifier file;

	public CompositeVerifier(String category,
			ExceptionFactory<?> generalExceptionFactory,
			ExceptionFactory<?> nullExceptionFactory) {
		this(category, generalExceptionFactory, nullExceptionFactory, null,
				null, null, null);
	}

	public CompositeVerifier(String category,
			ExceptionFactory<?> generalExceptionFactory,
			ExceptionFactory<?> nullExceptionFactory, TextVerifier text,
			NumberVerifier number, CollectionAndArrayVerifier collection,
			FileVerifier file) {
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
