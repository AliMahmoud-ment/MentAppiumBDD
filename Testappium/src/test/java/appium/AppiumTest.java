package appium;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;

public class AppiumTest {

	@Test
	public void configureAppium() throws MalformedURLException {

		AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
		service.start();

		// Create capabilities
		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("emulator-5554");
		options.setApp(System.getProperty("user.dir") + "\\src\\test\\java\\resources\\Calculator.apk");

		// Create an object for AndroidDriver
		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		
		// Perform actions using the driver
		driver.findElement(AppiumBy.accessibilityId("5")).click();
		driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"multiply\"]")).click();
		driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"6\"]")).click();
		driver.findElement(By.xpath("//android.widget.ImageButton[@content-desc=\"equals\"]")).click();

		// Close the driver
		driver.quit();
//         service.start();

	}
}
