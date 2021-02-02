package com.aom.stepDefinition;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import com.aom.support.*;
import com.cucumber.listener.Reporter;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/com/aom/features", glue = { "com.aom.stepDefinition" }, plugin = {
		"com.cucumber.listener.ExtentCucumberFormatter:target/cucumber-reports/report.html" }, monochrome = true, tags = "@office",dryRun = true)
public class Runner {

	@BeforeClass
	public static void ExecutionContext() throws UserException {
		System.out.println("Execution Started");
		TestContext testContext = new TestContext();
		Hooks hooks = new Hooks(testContext);
		hooks.BeforeSteps();
	}

	@AfterClass
	public static void writeExtentReport() {
		Reporter.loadXMLConfig(new File(FileReaderManager.getInstance().getConfigReader().getReportConfigPath()));
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				TestContext testContext = new TestContext();

				testContext.getWebDriverManager().getDriver()
						.get(System.getProperty("user.dir") + "\\target\\cucumber-reports\\report.html");
			}
		});
	}

}