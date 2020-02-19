package tests;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import pageobjects.Login;

import static org.junit.Assert.assertTrue;

public class TestLoginUsingPageObjects {
    private WebDriver driver;
    private Login login;

    @Before
    public void setUp() {
        System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/vendor/geckodriver");
        driver = new FirefoxDriver();
        login = new Login(driver);
    }

    @Test
    public void succeeded() {
        login.with("tomsmith", "SuperSecretPassword!");
        assertTrue("success message not present", login.successMessagePresent());
    }

    @Test
    public void failed() {
        login.with("tomsmith", "bad password");
        assertTrue("failure message wasnt present after providing bad credentials", login.failureMessagePresent());
    }
    @After
    public void tearDown() {
        driver.quit();
    }
}
