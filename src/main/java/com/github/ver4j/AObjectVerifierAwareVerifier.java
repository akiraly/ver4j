package com.github.ver4j;

public abstract class AObjectVerifierAwareVerifier extends AVerifier {
	private final ObjectVerifier objectVerifier;

	public AObjectVerifierAwareVerifier(ObjectVerifier objectVerifier) {
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
