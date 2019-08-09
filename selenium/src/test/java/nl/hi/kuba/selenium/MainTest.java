package nl.hi.kuba.selenium;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainTest {
    WebDriver driver = new FirefoxDriver();

    @Test
    public void testSimple() throws InterruptedException {
        driver.manage().window().maximize();
        driver.get("http://www.humaninference.com/contact-us");
        driver.findElement(By.xpath("//input[@type='submit']")).submit();
        assertTrue(driver.getPageSource().contains("This field is required.")); // actually, this is always present
        driver.quit();
    }
}