package com.github.ver4j.arg;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionMessageInfo;
import com.github.ver4j.ExceptionTypeInfo;
import com.github.ver4j.arg.exception.ArgumentTypeVerificationException;
import com.github.ver4j.arg.exception.ArgumentVerificationException;
import com.github.ver4j.arg.exception.BatchArgumentVerificationException;
import com.github.ver4j.arg.exception.NullPointerArgumentVerificationException;
import com.github.ver4j.arg.exception.TextArgumentVerificationException;

public class ArgCompositeVerifier extends CompositeVerifier {

	public ArgCompositeVerifier(String category) {
		super(category, //
				new ExceptionMessageInfo("argument"), //
				ExceptionTypeInfo.of(ArgumentVerificationException.class), //
				ExceptionTypeInfo.of(ArgumentTypeVerificationException.class), //
				ExceptionTypeInfo
						.of(NullPointerArgumentVerificationException.class), //
				ExceptionTypeInfo.of(TextArgumentVerificationException.class), //
				ExceptionTypeInfo
						.of(BatchArgumentVerificationException.class) //
		);
	}

}
