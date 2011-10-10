package com.github.ver4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.Nonnull;

public class GeneralVerifierRegistry {
	private final GeneralVerifier global = new GeneralVerifier(null);

	private final Map<String, GeneralVerifier> verifiers = new ConcurrentHashMap<String, GeneralVerifier>();

	@Nonnull
	public GeneralVerifier getGlobal() {
		return global;
	}

	@Nonnull
	public GeneralVerifier getVerifyer(String category) {
		global.arg.notNull(category);

		GeneralVerifier result = verifiers.get(category);
		if (result == null) {
			result = new GeneralVerifier(category);
			verifiers.put(category, result);
		}

		return result;
	}

	public void setDisabled(boolean disabled) {
		global.setDisabled(disabled);

		for (GeneralVerifier verifier : verifiers.values()) {
			verifier.setDisabled(disabled);
		}
	}

}
