package com.aom.support;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverHelper {
	private static WebDriver _driver;

	public WebDriver getDriver() {
		if (_driver == null)
			_driver = createDriver();
		return _driver;
	}

	private WebDriver createDriver() {
		WebDriverManager.chromedriver().browserVersion(" 83.0.4103.97").setup();
		ChromeOptions options = new ChromeOptions();
		options.addArguments("start-maximized");
		options.addArguments("enable-automation");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-infobars");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--disable-browser-side-navigation");
		options.addArguments("--disable-gpu");
		_driver = new ChromeDriver(options);
		return _driver;
	}

	public void closeDriver() {
		_driver.close();
		_driver.quit();
	}
}
