package com.github.ver4j;

import javax.annotation.Nonnull;

import com.github.ver4j.arg.ArgCompositeVerifier;
import com.github.ver4j.result.ResultCompositeVerifier;
import com.github.ver4j.state.StateCompositeVerifier;

public class GeneralVerifier extends AVerifier {
	private final String category;

	public final ArgCompositeVerifier arg;

	public final StateCompositeVerifier state;

	public final ResultCompositeVerifier result;

	public GeneralVerifier(String category) {
		this(category, new ArgCompositeVerifier(category),
				new StateCompositeVerifier(category),
				new ResultCompositeVerifier(category));
	}

	public GeneralVerifier(String category, @Nonnull ArgCompositeVerifier arg,
			@Nonnull StateCompositeVerifier state,
			@Nonnull ResultCompositeVerifier result) {
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
	@Nonnull
	public ExceptionFactory getExceptionFactory() {
		return arg.getExceptionFactory();
	}

	@Override
	public void setDisabled(boolean disabled) {
		super.setDisabled(disabled);
		arg.setDisabled(disabled);
		state.setDisabled(disabled);
		result.setDisabled(disabled);
	}
}
