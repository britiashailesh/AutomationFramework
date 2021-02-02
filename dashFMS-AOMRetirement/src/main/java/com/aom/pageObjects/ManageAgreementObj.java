package com.aom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ManageAgreementObj {
	WebDriver _driver;

	public ManageAgreementObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this._driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Name')]/following-sibling::p")
	protected WebElement CompanyName;

	@FindBy(how = How.XPATH, using = "//textarea[@formcontrolname='comment']")
	protected WebElement CommentsSection;

	@FindBy(how = How.XPATH, using = "//button[text()='Submit']")
	protected WebElement Submit_Button;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Yes')]")
	protected WebElement button_popUpYes;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'successfully')]")
	protected WebElement ConfirmationText;
}
