package com.aom.stepDefinition;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.apache.commons.codec.binary.Base64;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import com.aom.Methods.Login;
import com.aom.support.TestContext;
import com.aom.support.UserException;
import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import cucumber.api.Scenario;
import cucumber.api.java.After;

public class Hooks {
	TestContext _testContext;
	Login _login;
	WebDriver _driver;

	public Hooks(TestContext context) {
		_testContext = context;
	}

	public void BeforeSteps() throws UserException {
		_driver = _testContext.getWebDriverManager().getDriver();
		_driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);
		_login = _testContext.getPageObjectManager().getLoginMethod();
		_login.AccessApplication();
		_login.EnterEmail("s@realogy.com");
		//_login.EnterUserCredentials();
		_login.StaySignedIn();
	}

	public String getBase64Screenshot(Scenario scenario) throws IOException {
		String encodedBase64 = null;
		FileInputStream fileInputStream = null;
		String screenshotName = scenario.getId().replaceAll("-", "_").replaceAll(";", "_");
		File sourcePath = ((TakesScreenshot) _testContext.getWebDriverManager().getDriver())
				.getScreenshotAs(OutputType.FILE);
		File destinationPath = new File(
				System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
		Files.copy(sourcePath, destinationPath);
		try {
			fileInputStream = new FileInputStream(destinationPath);
			byte[] bytes = new byte[(int) destinationPath.length()];
			fileInputStream.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return "data:image/png;base64," + encodedBase64;
	}

	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		System.out.println(scenario.getStatus());
		if (scenario.isFailed()) {
			Reporter.addStepLog("Failed");
			try {
				Reporter.addScreenCaptureFromPath(getBase64Screenshot(scenario));
			} catch (IOException e) {
				System.out.println("Unable to take screenshot");
			}
		} else {
			Reporter.addStepLog("Passed");
			try {
				Reporter.addScreenCaptureFromPath(getBase64Screenshot(scenario));
			} catch (IOException e) {
				System.out.println("Unable to take screenshot");
			}
		}
	}

}
