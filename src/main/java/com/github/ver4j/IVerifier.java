package com.github.ver4j;

import javax.annotation.Nonnull;

public interface IVerifier extends ICategorized {

	boolean isDisabled();

	void setDisabled(boolean disabled);

	@Nonnull
	ExceptionFactory getExceptionFactory();
}
