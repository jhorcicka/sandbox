package cz.hk.gmc.fizzbuzz;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FizzBuzzTest {
    private FizzBuzz _fizzBuzz = new FizzBuzz();

    @Test
    public void testResponse() throws Exception {
        assertEquals(FizzBuzz.FIZZBUZZ, _fizzBuzz.response(0));
        assertEquals("1", _fizzBuzz.response(1));
        assertEquals("2", _fizzBuzz.response(2));
        assertEquals(FizzBuzz.FIZZ, _fizzBuzz.response(3));
        assertEquals("4", _fizzBuzz.response(4));
        assertEquals(FizzBuzz.BUZZ, _fizzBuzz.response(5));
        assertEquals(FizzBuzz.FIZZ, _fizzBuzz.response(6));
        assertEquals("7", _fizzBuzz.response(7));
        assertEquals("8", _fizzBuzz.response(8));
        assertEquals(FizzBuzz.FIZZ, _fizzBuzz.response(9));
        assertEquals(FizzBuzz.BUZZ, _fizzBuzz.response(10));
        assertEquals("11", _fizzBuzz.response(11));
        assertEquals(FizzBuzz.FIZZ, _fizzBuzz.response(12));
        assertEquals("13", _fizzBuzz.response(13));
        assertEquals("14", _fizzBuzz.response(14));
        assertEquals(FizzBuzz.FIZZBUZZ, _fizzBuzz.response(15));
    }
}