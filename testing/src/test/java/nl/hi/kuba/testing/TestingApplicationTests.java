package nl.hi.kuba.testing;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInfo;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TestingApplicationTests {

	void contextLoads() {
	}

	@Test
	public void whenExecutedThenSuccess() {
		assertTrue(true);
	}

	@RepeatedTest(value = 3, name = "Repeated test {currentRepetition}/{totalRepetitions}")
	public void whenExecutedThenRepeatedSuccess(TestInfo testInfo) {
		assertTrue(true);
	}

}
