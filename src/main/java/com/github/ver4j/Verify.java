package com.github.ver4j;

import javax.annotation.Nonnull;

import com.github.ver4j.arg.ArgCompositeVerifier;
import com.github.ver4j.result.ResultCompositeVerifier;
import com.github.ver4j.state.StateCompositeVerifier;

public class Verify {
	private static final GeneralVerifierRegistry CATEGORIZER = new GeneralVerifierRegistry();

	@Nonnull
	public static final ArgCompositeVerifier arg;

	@Nonnull
	public static final StateCompositeVerifier state;

	@Nonnull
	public static final ResultCompositeVerifier result;

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

	public static final void setDisabled(boolean disabled) {
		CATEGORIZER.setDisabled(disabled);
	}
}
