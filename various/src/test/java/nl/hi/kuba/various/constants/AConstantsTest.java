package nl.hi.kuba.various.constants;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class AConstantsTest {
    @Test
    public void testOne() throws Exception {
        final List<String> oneAliases = Arrays.asList(AConstants.ONE);
        final String[] expectedValues = {"one", "eins", "een"};

        for (final String expected : expectedValues) {
            assertTrue(oneAliases.contains(expected));
        }
    }
}