package com.bcahungary.acme;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest {

    private WebDriver driver;
    private final String loginUrl = TestUtils.properties.getProperty("login.url");
    private final String email = TestUtils.properties.getProperty("email");
    private final String password = TestUtils.properties.getProperty("password");

    @BeforeClass
    public void setUp() {
        driver = TestUtils.createDriver();
        driver.get(loginUrl);
    }

    @Test
    public void testLogin() {
        Assert.assertTrue(TestUtils.waitforDomComplete(driver), "DOM is not fully loaded.");

        TestUtils.login(driver);

        // Wait for the DOM to fully load after login attempt
        Assert.assertTrue(TestUtils.waitforDomComplete(driver), "DOM is not fully loaded after login.");

        // Get the current URL after login and check if the login was successful
        String currentUrl = driver.getCurrentUrl();
        String expectedUrlAfterLogin = TestUtils.properties.getProperty("expected.url.after.login");
        Assert.assertEquals(currentUrl, expectedUrlAfterLogin, "Login failed or wrong URL after login.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
