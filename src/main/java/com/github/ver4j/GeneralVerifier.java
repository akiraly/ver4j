package com.github.ver4j;

public class GeneralVerifier extends AVerifier {
	private final String category;

	public final CompositeVerifier arg;

	public final CompositeVerifier state;

	public final CompositeVerifier result;

	public GeneralVerifier(String category) {
		this(category, new CompositeVerifier(category, null, null),
				new CompositeVerifier(category, null, null),
				new CompositeVerifier(category, null, null));
	}

	public GeneralVerifier(String category, CompositeVerifier arg,
			CompositeVerifier state, CompositeVerifier result) {
		super();
		this.category = category;
		this.arg = arg;
		this.state = state;
		this.result = result;
	}

	@Override
	public final String getCategory() {
		return category;
	}

	@Override
	public void setEnabled(boolean enabled) {
		super.setEnabled(enabled);
		arg.setEnabled(enabled);
		state.setEnabled(enabled);
		result.setEnabled(enabled);
	}
}
