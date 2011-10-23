package com.github.ver4j;

public abstract class AVerifier implements IVerifier {
	private boolean disabled = false;

	@Override
	public final boolean isDisabled() {
		return disabled;
	}

	@Override
	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}
}
