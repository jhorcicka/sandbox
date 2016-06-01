package cz.hk.gmc.selenium;


import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class MainTest {
    private static FirefoxDriver driver;
    WebElement element;

    @BeforeClass
    public static void openBrowser() {
        driver = new FirefoxDriver();
    }

    @Test
    public void valid_UserCredential() {
        driver.get("http://www.humaninference.com");

        try {
            element = driver.findElement(By.xpath(".//*[@id='account_logout']/a"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        Assert.assertNotNull(element);
        System.out.println("Ending test " + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    @Test
    public void inValid_UserCredential() {
        System.out.println("Starting test " + new Object() {
        }.getClass().getEnclosingMethod().getName());
        driver.get("http://www.store.demoqa.com");
        driver.findElement(By.xpath(".//*[@id='account']/a")).click();
        driver.findElement(By.id("log")).sendKeys("testuser");
        driver.findElement(By.id("pwd")).sendKeys("Test@123");
        driver.findElement(By.id("login")).click();
        try {
            element = driver.findElement(By.xpath(".//*[@id='account_logout']/a"));
        } catch (Exception e) {
            // ignored
        }
        Assert.assertNotNull(element);
        System.out.println("Ending test " + new Object() {
        }.getClass().getEnclosingMethod().getName());
    }

    @AfterClass
    public static void closeBrowser() {
        //driver.quit();
    }
}