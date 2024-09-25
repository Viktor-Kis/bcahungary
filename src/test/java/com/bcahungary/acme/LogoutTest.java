package com.bcahungary.acme;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;

public class LogoutTest {

    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        driver = TestUtils.createDriver();
        TestUtils.login(driver);
    }

    @Test
    public void testLogout() {
        driver.findElement(By.linkText("Log Out")).click();
        String loginUrl = TestUtils.properties.getProperty("login.url");
        new WebDriverWait(driver, Duration.ofSeconds(10)).until(ExpectedConditions.urlToBe(loginUrl));

        String currentUrl = driver.getCurrentUrl();
        Assert.assertEquals(currentUrl, loginUrl, "A kijelentkezés nem sikerült, nem a bejelentkező oldalon vagyunk.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
