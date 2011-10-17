package com.github.ver4j.result;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionMessageInfo;
import com.github.ver4j.ExceptionTypeInfo;
import com.github.ver4j.result.exception.ClassCastResultException;
import com.github.ver4j.result.exception.NullPointerResultException;
import com.github.ver4j.result.exception.ResultVerificationException;

public class ResultCompositeVerifier extends CompositeVerifier {

	public ResultCompositeVerifier(String category) {
		super(category, //
				new ExceptionMessageInfo("result"), //
				ExceptionTypeInfo.of(ResultVerificationException.class), //
				ExceptionTypeInfo.of(ClassCastResultException.class), //
				ExceptionTypeInfo.of(NullPointerResultException.class) //
		);
	}

}
