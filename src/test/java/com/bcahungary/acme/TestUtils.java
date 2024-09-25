package com.bcahungary.acme;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestUtils {

    public static final Properties properties = new Properties();

    static {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("testconfig.properties");
            properties.load(fis);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(TestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(TestUtils.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                fis.close();
            } catch (IOException ex) {
                Logger.getLogger(TestUtils.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * This method checks if the DOM is fully loaded.
     *
     * @param driver
     * @return
     */
    public static boolean waitforDomComplete(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
        ExpectedCondition<Boolean> domLoadCondition = (WebDriver driver1)
                -> ((JavascriptExecutor) driver1).executeScript("return document.readyState").toString().equals("complete");

        return wait.until(domLoadCondition);
    }

    public static void login(WebDriver driver) {
        driver.get(TestUtils.properties.getProperty("login.url"));
        WebElement emailField = driver.findElement(By.id("email")); // Esetleg más attribútum alapján kell keresni
        WebElement passwordField = driver.findElement(By.id("password")); // Esetleg más attribútum alapján kell keresni

        emailField.sendKeys(TestUtils.properties.getProperty("email"));
        passwordField.sendKeys(TestUtils.properties.getProperty("password"));

        // Click the login button
        // TODO: missing ID 
        WebElement loginButton = driver.findElement(By.cssSelector("button.btn.btn-primary"));
        loginButton.click();
    }

    /**
     * This method create a WebDriver instance
     *
     * @return a WebDriver instance
     */
    public static WebDriver createDriver() {
        System.setProperty("webdriver.chrome.driver", TestUtils.properties.getProperty("chrome.driver.path"));
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        return driver;
    }
}
