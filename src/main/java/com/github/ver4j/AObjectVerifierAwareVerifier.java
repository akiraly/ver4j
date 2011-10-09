package com.github.ver4j;

import javax.annotation.Nonnull;

public abstract class AObjectVerifierAwareVerifier extends AVerifier {
	private final ObjectVerifier objectVerifier;

	public AObjectVerifierAwareVerifier(@Nonnull ObjectVerifier objectVerifier) {
		super();
		this.objectVerifier = objectVerifier;
	}

	protected final ObjectVerifier object() {
		return objectVerifier;
	}

	@Override
	public String getCategory() {
		return objectVerifier.getCategory();
	}
}
