package dwManagement;

import java.io.FileInputStream; 
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import Utilities.AndroidUtilities;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.cucumber.java.After;

public class DWManager {
	
	public AppiumDriverLocalService service;
	

	protected AndroidDriver driver;	
	private Properties properties;
	protected AndroidUtilities utilities;  
	protected IOSDriver iosDriver;
	AndroidUtilities androidUtilities;
	 public void pushPhoto() {
	        try {
	            String photo = System.getProperty("user.dir") + "\\Photo\\attachment.png";

	            // Command to push the image to the emulator
	            String command = "adb push " + photo + " /sdcard/Download/";

	            // Execute the command
	            Process process = Runtime.getRuntime().exec(command);

	            // Wait for the command to complete
	            process.waitFor();

	            // Check if the command was successful
	            if (process.exitValue() == 0) {
	                System.out.println("Image uploaded successfully.");

	                // Trigger a media scan so that the photo appears in the Photos app
	                String mediaScanCommand = "adb shell am broadcast -a android.intent.action.MEDIA_SCANNER_SCAN_FILE -d file:///sdcard/Download/attachment.png";
	                Process mediaScanProcess = Runtime.getRuntime().exec(mediaScanCommand);

	                // Wait for the media scan command to complete
	                mediaScanProcess.waitFor();

	                // Check if the media scan was successful
	                if (mediaScanProcess.exitValue() == 0) {
	                    System.out.println("Media scan completed successfully. Photo should now appear in the Photos app.");
	                } else {
	                    System.out.println("Failed to trigger media scan.");
	                }

	            } else {
	                System.out.println("Failed to upload the image.");
	            }
	        } catch (IOException | InterruptedException e) {
	            e.printStackTrace();
	        }
	    }


	//@BeforeSuite
	public void setUp() throws IOException {
		System.out.println("Setting up...");
		pushPhoto();
	    try {
	        loadProperties();
//	        UiAutomator2Options options = new UiAutomator2Options();
//	        options.setCapability("deviceName", properties.getProperty("DEVICE.NAME"));
//	        options.setCapability("app", properties.getProperty("APP.PATH.DW"));
//	        options.setCapability("autoGrantPermissions", "true"); 
//	        options.setCapability("appPackage", "ae.smartdubai.dubainow.enterprise");
//	        options.setCapability("appActivity", "ae.gov.dsg.mdubai.login.SplashScreenActivity");
	        
	        
	  	  DesiredCapabilities caps = new DesiredCapabilities();
	  			caps.setCapability("app",
	  					"C:\\Users\\ali.mahmoud\\git\\Testappium\\Testappium\\src\\test\\java\\resources\\ment.apk");
	  			caps.setCapability("deviceName", "emulator-5554");
	  			caps.setCapability("platformName", "Android");
	  			caps.setCapability("platformVersion", "14.0");
	  			caps.setCapability("automationName", "UiAutomator2");
	  			caps.setCapability("noReset", false);
//				
				caps.setCapability("fullReset", false);

				driver = new AndroidDriver(new URL("http://127.0.0.1:4723/"), caps);
			
	        
//	        URL appiumServerUrl = new URL(properties.getProperty("http://127.0.0.1:4723/ "));
//	        setDriver(new AndroidDriver(appiumServerUrl, caps));
	        getDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        System.out.println("AndroidDriver initialized successfully.");
	    } catch (IOException e) {
	        e.printStackTrace();  
	    }
	    
		
	    /*try {
	         loadProperties();
	         DesiredCapabilities options = new DesiredCapabilities();         
	         options.setCapability("deviceName", "iPhone 11");
	         options.setCapability("platformName", "iOS");
	         options.setCapability("platformVersion", "16.6.1"); 
	         options.setCapability("udid", "00008030-001D590E3610402E"); 
	         options.setCapability("bundleId", "com.deg.mdubai.enterprise");
	         options.setCapability("automationName", "XCUITest");  
		     URL appiumServerUrl = new URL(properties.getProperty("APPIUM.SERVER.URL"));
		     setIOSDriver(new IOSDriver(appiumServerUrl, options));
		     getIOSDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));	       
	         System.out.println("AndroidDriver initialized successfully.");
	    } catch (IOException e) {
	        e.printStackTrace();  
	    }*/
	}
	
	public IOSDriver getIOSDriver() {
		return iosDriver;
	}

	public void setIOSDriver(IOSDriver driver) {
		this.iosDriver = driver;
	}

	//@After
	public void tearDown() {	   
	    if (getDriver() != null) {	        
	        getDriver().quit();
	    }
	    System.out.println("Setting down...");
	}

	private void loadProperties() throws IOException {
		properties = new Properties();
		FileInputStream fis = new FileInputStream("src/main/resources/config.properties");
		properties.load(fis);
		fis.close(); 
		String appPath = properties.getProperty("APP.PATH.DW");
		if (appPath.startsWith(properties.getProperty("APP.PATH.DW"))) {
			String frameworkDir = System.getProperty("user.dir");
			properties.setProperty("APP.PATH.DW", frameworkDir + "/" + appPath);
		}
	}
	
	public AndroidDriver getDriver() {
		return driver;
	}

	public void setDriver(AndroidDriver driver) {
		this.driver = driver;
	}
}
