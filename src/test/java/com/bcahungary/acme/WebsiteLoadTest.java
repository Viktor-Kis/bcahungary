package com.bcahungary.acme;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class WebsiteLoadTest {

    private WebDriver driver;
    private final String baseUrl = "https://acme-test.uipath.com";
    private final String loginUrl = "https://acme-test.uipath.com/login";

    @BeforeClass
    public void setUp() {
        driver = TestUtils.createDriver();
    }

    @Test
    public void testWebsiteLoad() {
        driver.get(baseUrl);
        boolean isDomLoaded = TestUtils.waitforDomComplete(driver);
        String currentUrl = driver.getCurrentUrl();
        Assert.assertTrue(isDomLoaded && currentUrl.equals(loginUrl), "The website did not load correctly.");
    }

    @AfterClass
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
