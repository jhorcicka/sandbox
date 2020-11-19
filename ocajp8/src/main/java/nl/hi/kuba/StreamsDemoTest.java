package nl.hi.kuba;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class StreamsDemoTest {
    private static final List<String> SOURCE_LIST = Arrays.asList("1", "2", "3");

    @Test
    public void testLoop() {
        assertTrue(StreamsDemo.containsWithLoop(SOURCE_LIST, Arrays.asList("1")));
        assertTrue(StreamsDemo.containsWithLoop(SOURCE_LIST, Arrays.asList("2")));
        assertTrue(StreamsDemo.containsWithLoop(SOURCE_LIST, Arrays.asList("3")));
        assertTrue(StreamsDemo.containsWithLoop(SOURCE_LIST, Arrays.asList("2", "1")));
        assertTrue(StreamsDemo.containsWithLoop(SOURCE_LIST, Arrays.asList("3", "2", "1")));
        assertTrue(StreamsDemo.containsWithLoop(SOURCE_LIST, Arrays.asList("4", "5", "3")));
        assertFalse(StreamsDemo.containsWithLoop(SOURCE_LIST, Arrays.asList("4", "5", "6")));
    }

    public void testStream() {
        assertTrue(StreamsDemo.containsWithStream(SOURCE_LIST, Arrays.asList("1")));
        assertTrue(StreamsDemo.containsWithStream(SOURCE_LIST, Arrays.asList("2")));
        assertTrue(StreamsDemo.containsWithStream(SOURCE_LIST, Arrays.asList("3")));
        assertTrue(StreamsDemo.containsWithStream(SOURCE_LIST, Arrays.asList("2", "1")));
        assertTrue(StreamsDemo.containsWithStream(SOURCE_LIST, Arrays.asList("3", "2", "1")));
        assertTrue(StreamsDemo.containsWithStream(SOURCE_LIST, Arrays.asList("4", "5", "3")));
        assertFalse(StreamsDemo.containsWithStream(SOURCE_LIST, Arrays.asList("4", "5", "6")));
    }
}