package com.aom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class ViewAgreementTermsObj {
	WebDriver _driver;

	public ViewAgreementTermsObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this._driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//p[contains(text(),'Doing Business As')]/following-sibling::p")
	protected WebElement TradeName;

}
