package com.aom.pageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class AgreementHomePageObj {
	WebDriver _driver = null;

	public AgreementHomePageObj(WebDriver driver) {
		PageFactory.initElements(driver, this);
		this._driver = driver;
	}

	@FindBy(how = How.XPATH, using = "//span[text()='Agreement Terms']")
	protected WebElement AgreementTermsMenu;

	@FindBy(how = How.XPATH, using = "//span[text()='Tools']")
	protected WebElement ToolsMenu;

	@FindBy(how = How.XPATH, using = "//div[contains(text(),'Addendums - Pending Setup')]")
	protected WebElement pendingAddendumSetup_alert;

	@FindBy(how = How.XPATH, using = "//div[@class='carousel-inner']//span[contains(text(),'Agreements - Pending Agreement Terms Setup')]")
	protected WebElement pendingBillingtermsSetup_alert;

	@FindBy(how = How.XPATH, using = "//div[@class='carousel-inner']//span[contains(text(),'Agreements - Pending Approvals')]")
	protected WebElement pendingApproval_alert;

	@FindBy(how = How.XPATH, using = "//div[@class='carousel-inner']//span[contains(text(),'Executed office with No IFF')]")
	protected WebElement NoIFF_alert;

	@FindBy(how = How.XPATH, using = "//div[@class='carousel-inner']//span[contains(text(),'Executed office- IFF Pending Approval')]")
	protected WebElement IFFApproval_alert;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'itemB point')]//p[contains(text(),'Addendum')]")
	protected WebElement actionPanel_Addendum;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'itemB point')]//p[contains(text(),'View Agreement Terms')]")
	protected WebElement actionPanel_View;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'itemB point')]//p[contains(text(),'Manage Agreement Terms')]")
	protected WebElement actionPanel_Manage;

	@FindBy(how = How.XPATH, using = "//div[@class='itemB point ng-star-inserted']//p[contains(text(),'Review & Approve')]")
	protected WebElement actionPanel_Review;

	@FindBy(how = How.XPATH, using = "//div[@class='itemB point ng-star-inserted']//p[contains(text(),'Enter IFF details')]")
	protected WebElement actionPanel_EnterIFF;

	@FindBy(how = How.XPATH, using = "//div[@class='itemB point ng-star-inserted']//p[contains(text(),'Renewal')]")
	protected WebElement actionPanel_Renewal;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'Yes')]")
	protected WebElement button_popUpYes;

	@FindBy(how = How.XPATH, using = "//button[contains(text(),'No')]")
	protected WebElement button_popUpNo;

	@FindBy(how = How.XPATH, using = "//input[@placeholder='Search Agreement ID, Company ID or Company Name']")
	protected WebElement quicksearch;

	@FindBy(how = How.XPATH, using = "//div[@role='listbox']/mat-option/span//b")
	protected WebElement quicksearchList;

	@FindBy(how = How.XPATH, using = "//app-filteredagreementgrid//tr[1]/td[2]")
	protected WebElement gridTopRow;

	@FindBy(how = How.XPATH, using = "//span[contains(text(),'Search Results:')]")
	protected WebElement AdvancedSearchResultCount;

	@FindBy(how = How.XPATH, using = "//ul[contains(@class,'k-pager')]/li/a")
	protected List<WebElement> GridPaginationList;

	@FindBy(how = How.XPATH, using = "//a[@title='Go to the next page']/span")
	protected WebElement GridPaginationNextButton;

	@FindBy(how = How.XPATH, using = "//table[@class='k-grid-table']//td[@aria-colindex='1']")
	protected List<WebElement> gridRow;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'itemB')]//p[text()='Edit Addendum']")
	protected WebElement EditAddendum;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'itemB')]//p[text()='Edit Adhoc change']")
	protected WebElement EditAdhocChange;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'itemB')]//p[text()='View Addendum']")
	protected WebElement ViewAddendum;

	@FindBy(how = How.XPATH, using = "//div[contains(@class,'itemB')]//p[text()='View Adhoc change']")
	protected WebElement ViewAdhocChange;

	@FindBy(how = How.XPATH, using = "//div[@class='container popover-body']//span/p[contains(text(),'Delete')]")
	protected WebElement DeleteAddendum;

	@FindBy(how = How.XPATH, using = "//app-agreementactions-overlay/div/div[2]/span/p[contains(text(),'Enter Stipulation Details')]")
	protected WebElement actionPanel_EnterStipulation;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'Looks like something has gone wrong')]")
	protected WebElement dashError;

	@FindBy(how = How.XPATH, using = "//h2[contains(text(),'View Agreement Terms')]")
	protected WebElement ViewAgreementHeader;

}