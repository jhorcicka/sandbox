package cz.hk.gmc.selenium;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

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