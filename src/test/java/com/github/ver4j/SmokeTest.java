package com.github.ver4j;

import org.junit.Test;

public class SmokeTest {

	@Test
	public void testGlobalVerifier() {
		Verify.arg.notNull(1);
	}
}
