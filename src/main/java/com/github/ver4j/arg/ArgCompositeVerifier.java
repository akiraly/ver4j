package com.github.ver4j.arg;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionMessageInfo;
import com.github.ver4j.ExceptionTypeInfo;
import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.ClassCastArgumentException;
import com.github.ver4j.arg.exception.NullPointerArgumentException;

public class ArgCompositeVerifier extends CompositeVerifier {

	public ArgCompositeVerifier(String category) {
		super(category, //
				new ExceptionMessageInfo("argument"), //
				ExceptionTypeInfo.of(ArgumentVerificationException.class), //
				ExceptionTypeInfo.of(ClassCastArgumentException.class), //
				ExceptionTypeInfo.of(NullPointerArgumentException.class) //
		);
	}

}
