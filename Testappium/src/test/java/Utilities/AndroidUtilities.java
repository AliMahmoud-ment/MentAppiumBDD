package Utilities;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.ImmutableMap;

import dwManagement.DWManager;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;

public class AndroidUtilities extends DWManager {

	private AndroidDriver driver;

	public AndroidUtilities(AndroidDriver driver) {
		this.driver = driver;
	}

	// Long Press Action
	public void longPressAction(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "duration", 4000));
	}

	// Move Back Action
	public void goBack() {
		KeyEvent backKeyEvent = new KeyEvent().withKey(AndroidKey.BACK);
		driver.pressKey(backKeyEvent);
	}

	// Move to Home
	public void goToHome() {
		KeyEvent backKeyEvent = new KeyEvent().withKey(AndroidKey.HOME);
		driver.pressKey(backKeyEvent);
	}

	// Scroll to specific element by using androidUiAutomator
	public void scrollToElement(String eleText) {
	    try {
	        System.out.println("Scrolling to element with text: " + eleText);
	        
	        WebElement element = driver.findElement(AppiumBy.androidUIAutomator(
	                "new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + eleText + "\"));"));

	        System.out.println("Element found. Waiting for visibility...");
	        
	        // Wait for the element to be visible before interacting with it
	        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
	        wait.until(ExpectedConditions.visibilityOf(element));
	        
	        System.out.println("Element is now visible.");
	    } catch (NoSuchElementException e) {
	        // Handle exception if the element is not found after scrolling
//	        System.out.println("Element with text '" + eleText + "' not found after scrolling.");
	    }
	}


	// Scroll to specific co_ordinate
	public void scrollToCo_ordinate() {
		boolean canScrollMore = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture",
				ImmutableMap.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent",
						3.0));
	}

	// Swipe Action to specific element
	public void swipeToElement(WebElement ele) {
		((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "direction", "left", "percent", 0.75));
	}
	
	 public void swipeToElement(By by) {
	        RemoteWebElement element = (RemoteWebElement) driver.findElement(by);
	        ((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
	                ImmutableMap.of("elementId", element.getId(), "direction", "left", "percent", 0.75));
	    }

	// Drag And Drop Action to specific element
	public void DragAndDrop(WebElement ele, int X, int Y) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId(), "endX", X, "endY", Y));
	}

	// Rotate at 90
	public void rotateLandscape() {
		DeviceRotation landscape = new DeviceRotation(0, 0, 90);
		driver.rotate(landscape);
	}

	// Set text to clipboard
	public void setClipboardText(String text) {
		driver.setClipboardText(text);
	}

	// Get text from clipboard
	public String getClipboardText() {
		return driver.getClipboardText();
	}

	// KeyBoard Actions
	public void pressKey(AndroidKey key) {
		if (driver == null) {
			handleException("Driver is not initialized", null);
			return;
		}

		try {
			driver.pressKey(new KeyEvent(key));
		} catch (Exception e) {
			handleException("Failed to press key", e);
		}
	}

	public void typeText(String text) {
		if (driver == null) {
			handleException("Driver is not initialized", null);
			return;
		}

		try {
			for (char c : text.toCharArray()) {
				pressKey(AndroidKey.valueOf("DIGIT_" + c));
			}
		} catch (Exception e) {
			handleException("Failed to type text", e);
		}
	}

	private void handleException(String message, Exception e) {
		System.err.println(message);
		if (e != null) {
			e.printStackTrace();
		}
	}
	
	// Swipe left action  
		public void swipeLeft(By by) {
	        RemoteWebElement element = (RemoteWebElement) driver.findElement(by);
	        driver.executeScript("gesture: swipe", Map.of("elementId", element.getId(), "percentage", 50, "direction", "left"));
	    }

		public void swipeRight(By by) {
	        RemoteWebElement element = (RemoteWebElement) driver.findElement(by);
	        driver.executeScript("gesture: swipe", Map.of("elementId", element.getId(), "percentage", 50, "direction", "right"));
	    }
		
		public void swipeUp(By by) {
			RemoteWebElement element = (RemoteWebElement) driver.findElement(by);
			driver.executeScript("gesture: swipe", Map.of("elementId", element.getId(),  "percentage", 50, "direction", "up"));
	    }
		
		public void swipeDown(By by) {
			RemoteWebElement element = (RemoteWebElement) driver.findElement(by);
			driver.executeScript("gesture: swipe", Map.of("elementId", element.getId(),  "percentage", 50, "direction", "down"));
	    }
		
		public void swipeUpForDocument(By by) {
			RemoteWebElement element = (RemoteWebElement) driver.findElement(by);
			driver.executeScript("gesture: swipe", Map.of("elementId", element.getId(),  "percentage", 20, "direction", "up"));
	    }
}