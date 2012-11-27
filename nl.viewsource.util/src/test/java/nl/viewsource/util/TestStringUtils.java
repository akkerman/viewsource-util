package nl.viewsource.util;

import org.junit.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Modifier;

import static nl.viewsource.util.StringUtils.isInteger;
import static org.junit.Assert.*;

public class TestStringUtils {

    public final String TEST_NULL = null;
    public final String TEST_EMPTY = "";
    public final String TEST_WHITE = "	\n	 \t	   ";

    @Test
    public void isEmpty_null() {
        assertTrue(StringUtils.isEmpty(TEST_NULL));
    }

    @Test
    public void isEmpty_empty() {
        assertTrue(StringUtils.isEmpty(TEST_EMPTY));
    }

    @Test
    public void isEmpty_whitespace() {
        assertTrue(StringUtils.isEmpty(TEST_WHITE));
    }

    @Test
    public void notNull_null() {
        final String result = StringUtils.notNull(TEST_NULL);
        assertNotNull(result);
    }

    @Test
    public void notNull_empty() {
        final String result = StringUtils.notNull(TEST_EMPTY);
        assertNotNull(result);
        assertEquals(TEST_EMPTY, result);
    }

    @Test
    public void notNull_whitespace() {
        final String result = StringUtils.notNull(TEST_WHITE);
        assertNotSame(null, result);
        assertEquals(TEST_WHITE, result);
    }

    @Test
    public void isInteger_whitespace() {
        final String input = TEST_WHITE;
        assertFalse(isInteger(input));
    }

    @Test
    public void isInteger_negative() {
        final String input = String.valueOf(-1);
        assertTrue(isInteger(input));
    }

    @Test
    public void isInteger_long() {
        final String input = String.valueOf(Long.MAX_VALUE);
        assertTrue(isInteger(input));
    }

    @Test
    public void isInteger_float() {
        final String input = String.valueOf(12.4f);
        assertFalse(isInteger(input));
    }

    @Test
    public void zeroPad_Null() {
        final String input = TEST_NULL;
        final String expected = "0000000000";

        final String result = StringUtils.zeroPad(input, 10);
        assertEquals(expected, result);
    }

    @Test
    public void zeroPad_10() {
        final String input = "2008";
        final String expected = "0000002008";

        final String result = StringUtils.zeroPad(input, 10);
        assertEquals(expected, result);
    }

    @Test
    public void testZeroPass$11Null() {
        final String input = TEST_NULL;
        final String expected = "00000000000";

        final String result = StringUtils.zeroPad(input, 11);
        assertEquals(expected, result);
    }

    @Test
    public void zeroPad_50() {
        final String input = "van1974tot2008";
        final String expected = "000000000000000000000000000000000000van1974tot2008";
        final int size = 50;

        final String result = StringUtils.zeroPad(input, size);
        assertEquals(expected, result);
    }

    @Test
    public void zeroPad_TooLarge() {
        final String input = "112233445566778899";
        final String expected = input;

        final String result = StringUtils.zeroPad(input, 10);
        assertEquals(expected, result);
    }


    @Test
    public void lpadd_Null() {
        final String input = TEST_NULL;
        final String expected = "----------";

        final String result = StringUtils.lpad(input, 10, '-');
        assertEquals(expected, result);
    }

    @Test
    public void lpadd_10() {
        final String input = "2008";
        final String expected = "------2008";

        final String result = StringUtils.lpad(input, 10, '-');
        assertEquals(expected, result);
    }

    @Test
    public void lpadd_11Null() {
        final String input = TEST_NULL;
        final String expected = "===========";

        final String result = StringUtils.lpad(input, 11, '=');
        assertEquals(expected, result);
    }

    @Test
    public void lpadd_50() {
        final String input = "van1974tot2008";
        final String expected = "////////////////////////////////////van1974tot2008";
        final int size = 50;

        final String result = StringUtils.lpad(input, size, '/');
        assertEquals(expected, result);
    }

    @Test
    public void lpadd_TooLarge() {
        final String input = "112233445566778899";
        final String expected = input;

        final String result = StringUtils.lpad(input, 10, ' ');
        assertEquals(expected, result);
    }

    @Test
    public void rpadd_Null() {
        final String input = TEST_NULL;
        final String expected = "----------";

        final String result = StringUtils.rpad(input, 10, '-');
        assertEquals(expected, result);
    }

    @Test
    public void rpadd_10() {
        final String input = "2008";
        final String expected = "2008------";

        final String result = StringUtils.rpad(input, 10, '-');
        assertEquals(expected, result);
    }

    @Test
    public void rpadd_11Null() {
        final String input = TEST_NULL;
        final String expected = "===========";

        final String result = StringUtils.rpad(input, 11, '=');
        assertEquals(expected, result);
    }

    @Test
    public void rpadd_50() {
        final String input = "van1974tot2008";
        final String expected = "van1974tot2008////////////////////////////////////";
        final int size = 50;

        final String result = StringUtils.rpad(input, size, '/');
        assertEquals(expected, result);
    }

    @Test
    public void rpadd_TooLarge() {
        final String input = "112233445566778899";
        final String expected = input;

        final String result = StringUtils.rpad(input, 10, ' ');
        assertEquals(expected, result);
    }


    @Test
    public void firstToUpper_null() {
        final String result = StringUtils.firstToUpper(TEST_NULL);
        assertEquals(null, result);
    }

    @Test
    public void firstToUpper_whitespace() {
        final String result = StringUtils.firstToUpper(TEST_WHITE);
        assertSame(TEST_WHITE, result);
    }

    @Test
    public void firstToUpper_length1() {
        final String input = "a";
        final String expected = "A";

        final String result = StringUtils.firstToUpper(input);
        assertEquals(expected, result);
    }

    @Test
    public void firstToUpper_length1digit() {
        final String input = "2";
        final String expected = input;

        final String result = StringUtils.firstToUpper(input);
        assertEquals(expected, result);
    }

    @Test
    public void firstToUpper_word() {
        final String input = "java";
        final String expected = "Java";

        final String result = StringUtils.firstToUpper(input);
        assertEquals(expected, result);
    }

    @Test
    public void firstToUpper_sentence() {
        final String input = "java TESTING with  JUnit";
        final String expected = "Java TESTING with  JUnit";

        final String result = StringUtils.firstToUpper(input);
        assertEquals(expected, result);
    }

    @Test
    public void testClass$isFinal() {
        int modifiers = StringUtils.class.getModifiers();
        assertTrue(Modifier.isFinal(modifiers));
    }

    @Test
    public void testClass$onePrivateDefaultConstructor() throws NoSuchMethodException {
        Constructor<?>[] constructors = StringUtils.class.getDeclaredConstructors();
        assertEquals("exactly one constructor expected", 1, constructors.length);
        Constructor<?> constructor = constructors[0];
        assertFalse("constructor should not be accessible", constructor.isAccessible());
        int modifiers = constructors[0].getModifiers();
        assertTrue("constructor should be default", Modifier.isPrivate(modifiers));
    }

    @Test
    public void boostTestCoverage_instantiatePrivateConstructor() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        Constructor<StringUtils> constructor = StringUtils.class.getDeclaredConstructor();
        constructor.setAccessible(true);
        constructor.newInstance();
    }
}
