package com.aom.Methods;

import org.openqa.selenium.WebDriver;

import com.aom.pageObjects.LoginObj;
import com.aom.support.*;

public class Login extends LoginObj {
	WebDriver _driver = null;
	BaseClass _baseClass = new BaseClass();

	public Login(WebDriver driver) {
		super(driver);
		this._driver = driver;
	}

	public void AccessApplication() throws UserException {

		_driver.navigate().to(GetProperty.getApplicationUrl(GetProperty.getEnvironment()));
		_baseClass.ClickElement(button_ConnectDFMS, _driver);
		_baseClass.WaitForPageLoad(_driver);
	}

	public void EnterEmail(String email) throws UserException {
		_baseClass.enterData(emailId, _driver, email);
		_baseClass.ClickElement(button_Next, _driver);
		_baseClass.WaitForPageLoad(_driver);
	}

	public void EnterUserCredentials() throws UserException {

		_baseClass.enterData(EmailField, _driver, "Abhishek.Suvarna@realogy.com");
		_baseClass.enterData(Password, _driver, "Chelseafc2020$");
		_baseClass.ClickElement(OktaSubmit, _driver);

	}

	public void StaySignedIn() throws UserException {
		_baseClass.WaitForPageLoad(_driver);
		_baseClass.ClickElement(button_Yes, _driver);
	}

	public boolean Test1Method() throws UserException {
		return _baseClass.verifyIsElementPresent(DFMS_Icon, _driver);
	}

}
