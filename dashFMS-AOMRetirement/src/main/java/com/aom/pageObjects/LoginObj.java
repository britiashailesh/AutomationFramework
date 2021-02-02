package com.aom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class LoginObj {
	WebDriver _driver = null;

	public LoginObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this._driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//span[text()='Connect to dash-FMS']")
	protected WebElement button_ConnectDFMS;

	@FindBy(how = How.XPATH, using = "//input[@type='email']")
	protected WebElement emailId;

	@FindBy(how = How.XPATH, using = "//input[@value='Next']")
	protected WebElement button_Next;

	@FindBy(how = How.XPATH, using = "//input[@value='Yes']")
	protected WebElement button_Yes;

	@FindBy(how = How.XPATH, using = "//span[text() = 'Billing Terms']")
	protected WebElement BillingTermsMenu;

	@FindBy(how = How.ID, using = "okta-signin-username")
	protected WebElement EmailField;

	@FindBy(how = How.ID, using = "okta-signin-password")
	protected WebElement Password;

	@FindBy(how = How.ID, using = "okta-signin-submit")
	protected WebElement OktaSubmit;

	@FindBy(how = How.XPATH, using = "//*[@id=\"form8\"]/div[2]/input")
	protected WebElement OktaSendPush;

	@FindBy(how = How.XPATH, using = "//div[@class='dashLogoLink ng-tns-c0-01']")
	protected WebElement DFMS_Icon;
}
