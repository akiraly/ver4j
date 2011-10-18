package com.github.ver4j;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import junit.framework.Assert;

import org.junit.Test;

public class ExceptionMessageInfoTest {

	@Test
	public void testMessageInfo() {
		ExceptionMessageInfo messageInfo = new ExceptionMessageInfo("Test")
				.appendCause("the sun is not shining");
		Assert.assertEquals(
				"[%s] Test verification failed because the sun is not shining.",
				messageInfo.getDefaultMessageTemplate());

		String message = messageInfo.createMessage(null, null,
				new Object[] { "1" });
		Assert.assertEquals(
				"[1] Test verification failed because the sun is not shining.",
				message);

		message = messageInfo.createMessage(Locale.GERMAN, "%.2f",
				new Object[] { 3.14 });
		Assert.assertEquals("3,14", message);

		Map<Object, Object> autoArgs = new LinkedHashMap<Object, Object>();
		autoArgs.put("key1", "value");
		autoArgs.put("key2", 1.23);
	}
}
