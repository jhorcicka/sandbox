package nl.hi.kuba.testing;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;

class TestingApplicationTests {
    @Test
    void whenExecutedThenSuccess() {
        assertTrue(true);
    }

    @RepeatedTest(value = 3, name = "Repeated test {currentRepetition}/{totalRepetitions}")
    void whenExecutedThenRepeatedSuccess(TestInfo testInfo) {
        assertTrue(true);
    }

    @Test
    void testMutationMethod() {
        assertFalse(TestingApplication.isPalindrome(null));
        assertFalse(TestingApplication.isPalindrome("abc"));
        assertFalse(TestingApplication.isPalindrome("a"));
        assertTrue(TestingApplication.isPalindrome(""));
        assertTrue(TestingApplication.isPalindrome("abcddcba"));
    }
}
