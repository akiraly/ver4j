package com.github.ver4j;

import javax.annotation.Nonnull;

public abstract class AObjectVerifierAwareVerifier extends AVerifier {
	private final ObjectVerifier objectVerifier;

	public AObjectVerifierAwareVerifier(@Nonnull ObjectVerifier objectVerifier) {
		super();
		this.objectVerifier = objectVerifier;
	}

	@Nonnull
	protected final ObjectVerifier getObjectVerifier() {
		return objectVerifier;
	}

	@Override
	public final String getCategory() {
		return objectVerifier.getCategory();
	}
}
