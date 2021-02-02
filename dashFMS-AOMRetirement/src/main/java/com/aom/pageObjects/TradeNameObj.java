package com.aom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class TradeNameObj {
	protected WebDriver _driver;

	public TradeNameObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this._driver = driver;
	}

	@FindBy(how = How.XPATH, using = "(//input[@formcontrolname='tradeName'])[1]")
	protected WebElement ManageTradeNameAgreement;

	@FindBy(how = How.XPATH, using = "(//input[@formcontrolname='tradeName'])[2]")
	protected WebElement ManageTradeNameOffice;

}
