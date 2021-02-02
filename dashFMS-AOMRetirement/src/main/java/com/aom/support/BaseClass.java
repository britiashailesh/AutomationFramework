package com.aom.support;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseClass {
	private WebDriverWait _wait;
	private int defaultTimeOut = 30;

	public void ClickElement(WebElement element, WebDriver driver) throws UserException {
		_wait = new WebDriverWait(driver, defaultTimeOut);
		_wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
	}

	public void WaitForPageLoad(WebDriver driver) {
		_wait = new WebDriverWait(driver, 60);
		ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").toString()
						.equals("complete");
			}
		};
		_wait.until(expectation);
	}

	public void WaitForElementClickable(WebElement element, WebDriver driver) throws UserException {
		_wait = new WebDriverWait(driver, 60);
		_wait.until(ExpectedConditions.elementToBeClickable(element));
	}

	public void enterData(WebElement element, WebDriver driver, String data) throws UserException {
		_wait = new WebDriverWait(driver, defaultTimeOut);
		_wait.until(ExpectedConditions.visibilityOf(element));
		element.click();
		element.clear();
		element.sendKeys(data);
	}

	public void selectFromDropdown(WebDriver driver, WebElement dropdownbutton, String dropdownText)
			throws UserException {
		_wait = new WebDriverWait(driver, defaultTimeOut);
		WebElement element = _wait.until(ExpectedConditions.visibilityOf(dropdownbutton));
		ClickElement(element, driver);
		try {
			Actions actions = new Actions(driver);
			WebElement element1 = driver
					.findElement(By.xpath("//div[@role='option']/*[contains(text(),'" + dropdownText + "')]"));
			WebElement element2 = _wait.until(ExpectedConditions.visibilityOf(element1));
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element2);
			actions.click(element2).build().perform();

		} catch (Exception e) {
			throw new UserException("Cant select Value " + dropdownText);
		}
	}

	public boolean verifyIsElementPresent(WebElement element, WebDriver driver) throws UserException {
		boolean result = false;
		try {
			WebDriverWait _wait = new WebDriverWait(driver, defaultTimeOut);
			WebElement _element = _wait.until(ExpectedConditions.visibilityOf(element));
			if (_element.isDisplayed())
				result = true;
		} catch (ElementNotVisibleException msg) {
			throw new UserException("Element not found" + element.getText());
		}
		return result;
	}

	public boolean verifyElementNotPresent(WebElement element, WebDriver _driver) throws UserException {
		boolean result = true;
		try {
			WebDriverWait _wait = new WebDriverWait(_driver, 5);
			WebElement _element = _wait.until(ExpectedConditions.visibilityOf(element));
			if (_element.isDisplayed())
				result = false;
		} catch (ElementNotVisibleException msg) {
			throw new UserException("Element not found" + element.getText());
		}
		return result;
	}

}
