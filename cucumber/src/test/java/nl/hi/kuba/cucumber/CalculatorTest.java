package nl.hi.kuba.cucumber;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"classpath:features/calculator.feature", "classpath:features/calculator-scenario-outline.feature"}
)
public class CalculatorTest {
}
