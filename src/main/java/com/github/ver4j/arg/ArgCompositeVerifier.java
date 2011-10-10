package com.github.ver4j.arg;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionFactory;
import com.github.ver4j.ExceptionInfo;
import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.NullPointerArgumentException;

public class ArgCompositeVerifier extends CompositeVerifier {

	public ArgCompositeVerifier(String category) {
		super(category, //
				new ExceptionFactory("Argument"), //
				ExceptionInfo.of(ArgumentVerificationException.class), //
				ExceptionInfo.of(NullPointerArgumentException.class) //
		);
	}

}
