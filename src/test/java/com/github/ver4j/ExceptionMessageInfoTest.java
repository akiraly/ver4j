package com.github.ver4j;

import java.util.Locale;

import junit.framework.Assert;

import org.junit.Test;

public class ExceptionMessageInfoTest {

	@Test
	public void testMessageInfo() {
		ExceptionMessageInfo messageInfo = new ExceptionMessageInfo("Test")
				.appendCause("the sun is shining");
		Assert.assertEquals(
				"Test (%s) failed verification because the sun is shining.",
				messageInfo.getDefaultMessageTemplate());

		String message = messageInfo.createMessage(null, null,
				new Object[] { "1" });
		Assert.assertEquals(
				"Test (1) failed verification because the sun is shining.",
				message);

		message = messageInfo.createMessage(Locale.GERMAN, "%.2f",
				new Object[] { 3.14 });
		Assert.assertEquals("3,14", message);
	}
}
