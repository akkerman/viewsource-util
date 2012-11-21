package nl.viewsource.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestStringUtils {

	public final String TEST_NULL = null;
	public final String TEST_EMPTY = "";
	public final String TEST_WHITE = "	\n	 \t	   ";

	@Test
	public void testIsEmpty$null() {
		assertTrue(StringUtils.isEmpty(TEST_NULL));
	}

	@Test
	public void testIsEmpty$empty() {
		assertTrue(StringUtils.isEmpty(TEST_EMPTY));
	}

	@Test
	public void testIsEmpty$whitespace() {
		assertTrue(StringUtils.isEmpty(TEST_WHITE));
	}

	@Test
	public void testNotNull$null() {
		final String result = StringUtils.notNull(TEST_NULL);
		assertNotNull(result);
	}

	@Test
	public void testNotNull$empty() {
		final String result = StringUtils.notNull(TEST_EMPTY);
		assertNotNull(result);
		assertEquals(TEST_EMPTY, result);
	}

	@Test
	public void testNotNull$whitespace() {
		final String result = StringUtils.notNull(TEST_WHITE);
		assertNotSame(null, result);
		assertEquals(TEST_WHITE, result);
	}

	private void testIsInteger(String input) {
		final boolean isInteger = StringUtils.isInteger(input);

		try {
			Integer.parseInt(input);			
			assertTrue("expected to fail parsing " + input, isInteger);			
		} catch (NumberFormatException e) {
			assertFalse("expected to pass parsing " + input, isInteger);			 
		}
	}

	@Test
	public void testIsInteger$whitespace() {
		final String input = TEST_WHITE;
		this.testIsInteger(input);
	}

	@Test
	public void testIsInteger$negative() {
		final String input = String.valueOf(-1);
		this.testIsInteger(input);
	}
	
	@Test
	public void testIsInteger$long() {
		final String input = String.valueOf(Long.MAX_VALUE);
		this.testIsInteger(input);
	}

	@Test
	public void testIsInteger$float() {
		final String input = String.valueOf(12.4f);
		this.testIsInteger(input);
	}

	@Test
	public void testZeroPadd$Null() {
		final String input = TEST_NULL;
		final String expected = "0000000000";

		final String result = StringUtils.zeroPadd(input, 10);
		assertEquals(expected, result);
	}
	
	@Test
	public void testZeroPadd$10() {
		final String input = "2008";
		final String expected = "0000002008";

		final String result = StringUtils.zeroPadd(input, 10);
		assertEquals(expected, result);
	}
	
	@Test
	public void testZeroPass$11Null() {
		final String input = TEST_NULL;
		final String expected = "00000000000";

		final String result = StringUtils.zeroPadd(input, 11);
		assertEquals(expected, result);
	}

	@Test
	public void testZeroPadd$50() {
		final String input = "van1974tot2008";
		final String expected = "000000000000000000000000000000000000van1974tot2008";
		final int size = 50;

		final String result = StringUtils.zeroPadd(input, size);
		assertEquals(expected, result);
	}

	@Test
	public void testZeroPadd$TooLarge() {
		final String input = "112233445566778899";
		final String expected = input;

		final String result = StringUtils.zeroPadd(input, 10);
		assertEquals(expected, result);
	}

	@Test
	public void testFirstToUpper$null() {
		final String result = StringUtils.firstToUpper(TEST_NULL);
		assertEquals(null, result);
	}

	@Test
	public void testFirstToUpper$whitespace() {
		final String result = StringUtils.firstToUpper(TEST_WHITE);
		assertTrue(TEST_WHITE == result);
	}

	@Test
	public void testFirstToUpper$length1() {
		final String input = "a";
		final String expected = "A";

		final String result = StringUtils.firstToUpper(input);
		assertEquals(expected, result);
	}

	@Test
	public void testFirstToUpper$length1digit() {
		final String input = "2";
		final String expected = input;

		final String result = StringUtils.firstToUpper(input);
		assertEquals(expected, result);
	}

	@Test
	public void testFirstToUpper$word() {
		final String input = "java";
		final String expected = "Java";

		final String result = StringUtils.firstToUpper(input);
		assertEquals(expected, result);
	}

	@Test
	public void testFirstToUpper$sentence() {
		final String input = "java TESTING with  junit";
		final String expected = "Java TESTING with  junit";

		final String result = StringUtils.firstToUpper(input);
		assertEquals(expected, result);
	}

}
