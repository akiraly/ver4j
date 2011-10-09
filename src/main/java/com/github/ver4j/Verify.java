package com.github.ver4j;

import javax.annotation.Nonnull;

public class Verify {
	private static final GeneralVerifierRegistry CATEGORIZER = new GeneralVerifierRegistry();

	@Nonnull
	public static final CompositeVerifier arg;

	@Nonnull
	public static final CompositeVerifier state;

	@Nonnull
	public static final CompositeVerifier result;

	static {
		GeneralVerifier verifier = CATEGORIZER.getGlobal();
		arg = verifier.arg;
		state = verifier.state;
		result = verifier.result;
	}

	@Nonnull
	public static final GeneralVerifier getVerifier(String category) {
		return CATEGORIZER.getVerifyer(category);
	}

	public static final void setEnabled(boolean enabled) {
		CATEGORIZER.setEnabled(enabled);
	}
}
