package Utilities;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import dwManagement.DWManager;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.functions.ExpectedCondition;

public class WaitUtilities extends DWManager{
    private AndroidDriver driver;

    public WaitUtilities(AndroidDriver driver) {
        super();
        this.driver = driver;
    }

    public static void waitForPageLoad(AndroidDriver driver, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        wait.until(pageLoadCondition);
    }
    public static void waitForAjax(AndroidDriver driver, Duration timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        ExpectedCondition<Boolean> ajaxCondition = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return (Boolean) ((JavascriptExecutor) driver).executeScript("return window.jQuery != undefined && jQuery.active == 0");
            }
        };
        wait.until(ajaxCondition);
    }

    // Wait for an element to be present in the DOM
    public WebElement waitForElementPresent(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for element with locator: " + locator, e);
        }
    }

    // Wait for an element to be clickable
    public WebElement waitForElementClickable(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.elementToBeClickable(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for element to be clickable with locator: " + locator, e);
        }
    }

    // Wait for an element to be visible
    public WebElement waitForElementVisible(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for element to be visible with locator: " + locator, e);
        }
    }

    // Wait for the text of an element to be present
    public boolean waitForTextToBePresent(By locator, String text, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.textToBe(locator, text));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for text to be present in element with locator: " + locator, e);
        }
    }

    // Wait for an element to be invisible
    public boolean waitForElementInvisible(By locator, Duration timeoutInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
            return wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
        } catch (TimeoutException e) {
            throw new RuntimeException("Timeout waiting for element to be invisible with locator: " + locator, e);
        }
    }

  

 
    
  
}
