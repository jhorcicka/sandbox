import org.junit.Test;

import static org.junit.Assert.*;

public class GreeterTest {
    Greeter greeter = new Greeter();

    @Test
    public void testSayHi() throws Exception {
        String name, greeting;

        name = "John";
        greeting = greeter.sayHi(name);
        assertTrue(greeting.contains(name) && greeting.contains("Hi"));

        name = "Mr. Doe";
        greeting = greeter.sayHi(name);
        assertTrue(greeting.contains(name) && greeting.contains("Hello"));
    }
}