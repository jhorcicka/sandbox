import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VariousTest {
    Entity e = new Entity();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testReturnFinal() {
        String f = getFinal();
        System.out.println("1: " + f);
        f = "hi";
        System.out.println("2: " + f);
    }

    private final String getFinal() {
        final String f = "final-value";

        return f;
    }
}
