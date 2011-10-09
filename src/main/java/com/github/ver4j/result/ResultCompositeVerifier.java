package com.github.ver4j.result;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionFactory;
import com.github.ver4j.result.exception.NullPointerResultException;
import com.github.ver4j.result.exception.ResultVerificationException;

public class ResultCompositeVerifier extends CompositeVerifier {

	public ResultCompositeVerifier(String category) {
		super(category, //
				ExceptionFactory.of(ResultVerificationException.class,
						"Result (%s) failed verification."), //
				ExceptionFactory.of(NullPointerResultException.class,
						"Result (%s) should not be null.") //
		);
	}

}
