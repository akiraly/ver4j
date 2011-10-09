package com.github.ver4j;

public class Verify {
	private static final GeneralVerifierRegistry CATEGORIZER = new GeneralVerifierRegistry();

	public static final CompositeVerifier arg;

	public static final CompositeVerifier state;

	public static final CompositeVerifier result;

	static {
		GeneralVerifier verifier = CATEGORIZER.getGlobal();
		arg = verifier.arg;
		state = verifier.state;
		result = verifier.result;
	}

	public static final GeneralVerifier getVerifier(String category) {
		return CATEGORIZER.getVerifyer(category);
	}

	public static final void setEnabled(boolean enabled) {
		CATEGORIZER.setEnabled(enabled);
	}
}
