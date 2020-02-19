package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;

public class TestLoginNoPageObject {
    private WebDriver driver;
    By loginFormLocator = By.id("login");

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver");
        driver = new FirefoxDriver();
    }

    @Test
    public void succeeded() {
        driver.get("http://the-internet.herokuapp.com/login");
        assertTrue("The login form is not present", driver.findElement(loginFormLocator).isDisplayed());
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button")).click();
        assertTrue("success message not present", driver.findElement(By.cssSelector(".flash.success")).isDisplayed());
    }

    @Test
    public void failed() {
        driver.get("http://the-internet.herokuapp.com/login");
        assertTrue("The login form is not present", driver.findElement(loginFormLocator).isDisplayed());
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("bad password");
        driver.findElement(By.cssSelector("button")).click();
        assertTrue("failure message wasnt present after providing bad credentials", driver.findElement(By.cssSelector(".flash.error")).isDisplayed());
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
