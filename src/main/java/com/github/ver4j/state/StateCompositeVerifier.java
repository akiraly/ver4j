package com.github.ver4j.state;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionMessageInfo;
import com.github.ver4j.ExceptionTypeInfo;
import com.github.ver4j.state.exception.BatchStateVerificationException;
import com.github.ver4j.state.exception.NullPointerStateVerificationException;
import com.github.ver4j.state.exception.StateOrderVerificationException;
import com.github.ver4j.state.exception.StateTypeVerificationException;
import com.github.ver4j.state.exception.StateVerificationException;
import com.github.ver4j.state.exception.TextStateVerificationException;

public class StateCompositeVerifier extends CompositeVerifier {

	public StateCompositeVerifier(String category) {
		super(category, //
				new ExceptionMessageInfo("state"), //
				ExceptionTypeInfo.of(StateVerificationException.class), //
				ExceptionTypeInfo.of(StateTypeVerificationException.class), //
				ExceptionTypeInfo
						.of(NullPointerStateVerificationException.class), //
				ExceptionTypeInfo.of(TextStateVerificationException.class), //
				ExceptionTypeInfo.of(BatchStateVerificationException.class), //
				ExceptionTypeInfo.of(StateOrderVerificationException.class) //
		);
	}
}
