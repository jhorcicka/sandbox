package nl.hi.kuba.testing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestingApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestingApplication.class, args);
	}

	public static int mutationTestMethod(final int input) {
		if (input >= 0) {
			return input;
		} else {
			return 0;
		}

	}

}
