package dWPageRepo;

import java.time.Duration;   
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.simple.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import Utilities.AndroidUtilities;
import Utilities.AssertionsUtils;
import Utilities.JSONUtils;
import Utilities.JavaScriptExecutorUtils;
import Utilities.SeleniumUtils;
import Utilities.WaitUtilities;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.cucumber.datatable.DataTable;

public class DWFunctionLibrary {

	AssertionsUtils assertions;
	AndroidUtilities androidutils;
	WaitUtilities waitutils;
	AndroidDriver driver;
	SeleniumUtils seleniumutils;	
	JavaScriptExecutorUtils jeutils;
	JSONUtils jsonutil;

	public DWFunctionLibrary(AndroidDriver driver) {
	    this.driver = driver;
	    this.seleniumutils = new SeleniumUtils(driver);
	    this.assertions = new AssertionsUtils(driver);
	    this.androidutils = new AndroidUtilities(driver);
	    this.waitutils = new WaitUtilities(driver);
	    this.jeutils = new JavaScriptExecutorUtils(driver);
	    this.jsonutil = new JSONUtils();
	}
	
	
	
	

   
    
    
    public void typeText(String text) {
        Actions actions = new Actions(driver);
        for (char character : text.toCharArray()) {
            actions.sendKeys(String.valueOf(character)).perform();
        }
    }
    
    
	public DWFunctionLibrary ClickStartBtn() {
		waitutils.waitForElementPresent(LoginPage.StartButton, Duration.ofSeconds(30));
		seleniumutils.clickElement(LoginPage.StartButton);
		return this;
	}
	public DWFunctionLibrary ClickLoginBtn() {
		waitutils.waitForElementPresent(LoginPage.LoginButton, Duration.ofSeconds(30));
		seleniumutils.clickElement(LoginPage.LoginButton);
		return this;
	}
	public DWFunctionLibrary ClickSearchBtn() {
		waitutils.waitForElementPresent(LoginPage.SrchButton, Duration.ofSeconds(30));
		seleniumutils.clickElement(LoginPage.SrchButton);
		return this;
	}
	public DWFunctionLibrary ClickContinueBtn() {
		waitutils.waitForElementPresent(LoginPage.ContinueButton, Duration.ofSeconds(30));
		seleniumutils.clickElement(LoginPage.ContinueButton);
		return this;
	}


}
