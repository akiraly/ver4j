package com.github.ver4j.result;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionMessageInfo;
import com.github.ver4j.ExceptionTypeInfo;
import com.github.ver4j.result.exception.BatchResultVerificationException;
import com.github.ver4j.result.exception.NullPointerResultVerificationException;
import com.github.ver4j.result.exception.ResultOrderVerificationException;
import com.github.ver4j.result.exception.ResultTypeVerificationException;
import com.github.ver4j.result.exception.ResultVerificationException;
import com.github.ver4j.result.exception.TextResultVerificationException;

public class ResultCompositeVerifier extends CompositeVerifier {

	public ResultCompositeVerifier(String category) {
		super(category, //
				new ExceptionMessageInfo("result"), //
				ExceptionTypeInfo.of(ResultVerificationException.class), //
				ExceptionTypeInfo.of(ResultTypeVerificationException.class), //
				ExceptionTypeInfo
						.of(NullPointerResultVerificationException.class), //
				ExceptionTypeInfo.of(TextResultVerificationException.class), //
				ExceptionTypeInfo.of(BatchResultVerificationException.class), //
				ExceptionTypeInfo.of(ResultOrderVerificationException.class) //
		);
	}

}
