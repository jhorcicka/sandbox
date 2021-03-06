import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import nl.hi.kuba.various.Entity;

public class EntityTest {
    Entity e = new Entity();

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetName() throws Exception {

    }

    @Test
    public void testSetName() throws Exception {
        String name = "new-name";
        e.setName(name);
        assertEquals(name, e.getName());
    }

    @Test
    public void testToString() throws Exception {
        assertNotNull(e.toString());
    }

    @Test
    public void testFail() throws Exception {
        //fail();
    }
}