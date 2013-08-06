package nl.viewsource.functional.option;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static nl.viewsource.functional.option.Option.None;
import static nl.viewsource.functional.option.Option.Some;
import static nl.viewsource.functional.option.Option.wrap;
import static org.junit.Assert.assertEquals;

/**
 * Test Option, Some and None.
 *
 * @author M. Akkerman
 */
public class TestOption {
    private List<Option<String>> names = null;

    @Before
    public void setup() {
        names = new ArrayList<Option<String>>();
        names.add(Some("Dean"));
        Option<String> none = None();
        names.add(none);
        names.add(Some("Wampler"));
    }

    @Test
    public void getOrElseUsesValueForSomeAndAlternativeForNone() {
        String[] expected = {"Dean", "Unknown!", "Wampler"};

        for (int i = 0; i < expected.length; i++) {
            Option<String> name = names.get(i);
            String value = name.getOrElse("Unknown!");
            assertEquals(expected[i], value);
        }
    }

    @Test
    public void hasNextWithGetUsesOnlyValuesForSomes() {
        String[] expected = {"Dean", "Unknown!", "Wampler"};

        for (int i = 0; i < expected.length; i++) {
            Option<String> name = names.get(i);
            if (name.hasValue()) {
                String value = name.get();
                assertEquals(expected[i], value);
            }
        }
    }

    @Test
    public void exampleMethodReturningOption_Some() {
        Option<String> opt = wrap("hello!");
        assertEquals(Some.class, opt.getClass());
        assertEquals("hello!", opt.getOrElse("str"));
    }

    @Test
    public void exampleMethodReturningOption_None() {
        Option<String> opt = wrap(null);
        assertEquals(None.class, opt.getClass());
        assertEquals("str", opt.getOrElse("str"));
    }
}
