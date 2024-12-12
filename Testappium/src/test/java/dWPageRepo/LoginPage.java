package dWPageRepo;

import org.openqa.selenium.By;

import io.appium.java_client.AppiumBy;

public class LoginPage {

	public static By AddressText = AppiumBy.xpath("//*[@text=\"Address\"]");
	
	
	
	public static By StartButton = AppiumBy.className("android.widget.Button");
	public static By LoginButton = AppiumBy.accessibilityId("Login");
	
	public static By SrchButton = AppiumBy.xpath("(//android.widget.ImageView[@content-desc=\"contacts\"])[1]");
	
}
