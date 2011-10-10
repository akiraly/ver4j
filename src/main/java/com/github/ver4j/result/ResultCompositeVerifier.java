package com.github.ver4j.result;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionFactory;
import com.github.ver4j.ExceptionInfo;
import com.github.ver4j.result.exception.NullPointerResultException;
import com.github.ver4j.result.exception.ResultVerificationException;

public class ResultCompositeVerifier extends CompositeVerifier {

	public ResultCompositeVerifier(String category) {
		super(category, //
				new ExceptionFactory("Result"), //
				ExceptionInfo.of(ResultVerificationException.class), //
				ExceptionInfo.of(NullPointerResultException.class) //
		);
	}

}
