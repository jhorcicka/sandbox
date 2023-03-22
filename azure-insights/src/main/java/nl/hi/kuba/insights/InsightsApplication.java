package nl.hi.kuba.insights;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InsightsApplication {
	private static final Logger LOGGER = LoggerFactory.getLogger(InsightsApplication.class);
	public static void main(String[] args) {
		LOGGER.info("InsightsApplication has just started. ");
		SpringApplication.run(InsightsApplication.class, args);
	}
}
