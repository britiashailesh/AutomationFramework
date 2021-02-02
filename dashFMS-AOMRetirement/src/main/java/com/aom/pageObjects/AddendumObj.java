package com.aom.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AddendumObj {
	WebDriver _driver = null;

	public AddendumObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this._driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Addendum')]")
	protected WebElement AddAddendumHeading;

	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='name']")
	protected WebElement AddendumName;

	@FindBy(how = How.XPATH, using = "//*[@formcontrolname='comments']")
	protected WebElement AddendumComment;

	@FindBy(how = How.XPATH, using = "//ng-select[@formcontrolname='category']")
	protected WebElement StipulationCategory;

	@FindBy(how = How.XPATH, using = "//ng-select[@formcontrolname='stipulationTypeKey']")
	protected WebElement StipulationType;

	@FindBy(how = How.XPATH, using = "//ng-select[@formcontrolname='effectiveFromTypeKey']")
	protected WebElement EffectiveFrom;

	@FindBy(how = How.XPATH, using = "//ng-select[@formcontrolname='effectiveToTypeKey']")
	protected WebElement EffectiveTo;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),'Agreement ')]//span[@class='checkmark']")
	protected WebElement ApplicableTo_Agreement;

	@FindBy(how = How.XPATH, using = "//label[contains(text(),' Select Offices ')]//span[@class='checkmark']")
	protected WebElement ApplicableTo_Office;

	@FindBy(how = How.XPATH, using = "//a[text()='Select Offices ']")
	protected WebElement SelectOfficesLink;

	@FindBy(how = How.XPATH, using = "//select/option[1]")
	protected WebElement SelectFirstOffice;

	@FindBy(how = How.XPATH, using = "//div[@id='multiSelect']//button[text()='Save']")
	protected WebElement SaveSelectedOffice;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add Stipulation')]")
	protected WebElement AddStipulation_Button;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Add another Stipulation')]")
	protected WebElement AddAnotheStipulation_Button;

	@FindBy(how = How.XPATH, using = "//button[@type='submit']")
	protected WebElement SubmitStipulation;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'View Addendum')]")
	protected WebElement ViewAddendumPageHeader;

	@FindBy(how = How.XPATH, using = "//*[contains(text(),'successfully')]")
	protected WebElement ConfirmationText;

	@FindBy(how = How.XPATH, using = "//a[text()=' View Agreement Terms']")
	protected WebElement viewAgreementHyperLink;

	@FindBy(how = How.XPATH, using = "//a[text()=' Manage Agreement Terms']")
	protected WebElement manageAgreementHyperLink;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Manage Agreement Terms')]")
	protected WebElement ManageBillingHeader;

	@FindBy(how = How.XPATH, using = "//html/body/div")
	protected WebElement StipulationDescription;

	@FindBy(how = How.XPATH, using = "//kendo-editor/div/iframe")
	protected WebElement Frame;

}
