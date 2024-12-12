package dWStepDefinitions;

import java.io.IOException;   
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.google.common.collect.ImmutableMap;

import Utilities.AndroidUtilities;
import Utilities.AssertionsUtils;
import Utilities.SeleniumUtils;
import Utilities.WaitUtilities;
import dWPageRepo.LoginPage;

import dWPageRepo.CommonElements;
import dWPageRepo.DWFunctionLibrary;
import dWPageRepo.DashboardPage;

import dwManagement.DWManager;
import io.appium.java_client.AppiumBy;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.*;






public class StepDefinitions extends DWManager {

	WaitUtilities wait;
	DWFunctionLibrary be;
	AssertionsUtils assertions;
	
	AndroidUtilities androidUtilities;
	SeleniumUtils su;

	@Before
	public void beforeScenario() throws IOException {
		setUp();
		be = new DWFunctionLibrary(driver);
		assertions = new AssertionsUtils(driver);
		wait = new WaitUtilities(driver);
		androidUtilities = new AndroidUtilities(driver);
	}

//    @After
	public void afterScenario() {
		tearDown();
	}
	
	
	@Given("the user is logged into the MENT app")
	public void user_logged_into_ment_application() {
//		be.SignIn();
//	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
//        WebElement startButton = wait.until(ExpectedConditions.elementToBeClickable(By.className("android.widget.Button")));
		
		
		be.ClickStartBtn();  		
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3)); 	
        be.typeText("amahmoud@getment.io");
        be.clickElementWithRetry(AppiumBy.accessibilityId("Continue"));      
        be.typeText("As12345!@#"); 
        be.ClickLoginBtn();
        be.ClickSearchBtn();
        
        
        
//		
	        
	}
	
	
	
	@When("the user imports contacts from their device")
	public void user_clicks_on_import() {
//	be.ClickImportContacts();

		
		
		
        
        
	}



	@AfterStep
	public void addScreenShot(Scenario scenario) {
		if (scenario.isFailed()) {
			// Check if the driver supports taking screenshots
			if (driver instanceof TakesScreenshot) {
				TakesScreenshot screenshotDriver = (TakesScreenshot) driver;

				// Check if getBddType() returns null
				if (scenario.getName() != null) {
					String scenarioName = scenario.getName();
					byte[] screenshot = screenshotDriver.getScreenshotAs(OutputType.BYTES);
					scenario.attach(screenshot, "image/png", scenarioName);
				} else {
					System.out.println("Scenario name is null. Cannot attach screenshot.");
				}
			} else {
				System.out.println("Driver does not support taking screenshots");
			}
		}
	}
}
