package com.github.ver4j.state;

import com.github.ver4j.CompositeVerifier;
import com.github.ver4j.ExceptionMessageInfo;
import com.github.ver4j.ExceptionTypeInfo;
import com.github.ver4j.state.exception.ClassCastStateException;
import com.github.ver4j.state.exception.NullPointerStateException;
import com.github.ver4j.state.exception.StateVerificationException;
import com.github.ver4j.state.exception.TextStateException;

public class StateCompositeVerifier extends CompositeVerifier {

	public StateCompositeVerifier(String category) {
		super(category, //
				new ExceptionMessageInfo("state"), //
				ExceptionTypeInfo.of(StateVerificationException.class), //
				ExceptionTypeInfo.of(ClassCastStateException.class), //
				ExceptionTypeInfo.of(NullPointerStateException.class), //
				ExceptionTypeInfo.of(TextStateException.class) //
		);
	}
}
