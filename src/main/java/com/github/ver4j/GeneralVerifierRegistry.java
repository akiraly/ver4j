package com.github.ver4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class GeneralVerifierRegistry {
	private final GeneralVerifier global = new GeneralVerifier(null);

	private final Map<String, GeneralVerifier> verifiers = new ConcurrentHashMap<String, GeneralVerifier>();

	public GeneralVerifier getGlobal() {
		return global;
	}

	public GeneralVerifier getVerifyer(String category) {
		global.arg.notNull(category);

		GeneralVerifier result = verifiers.get(category);
		if (result == null) {
			result = new GeneralVerifier(category);
			verifiers.put(category, result);
		}

		return result;
	}

	public void setEnabled(boolean enabled) {
		global.setEnabled(enabled);

		for (GeneralVerifier verifier : verifiers.values()) {
			verifier.setEnabled(enabled);
		}
	}

}
