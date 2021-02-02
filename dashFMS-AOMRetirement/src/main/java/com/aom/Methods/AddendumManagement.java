package com.aom.Methods;

import org.openqa.selenium.WebDriver;
import com.aom.pageObjects.AddendumObj;
import com.aom.support.BaseClass;
import com.aom.support.UserException;

public class AddendumManagement extends AddendumObj {
	public AddendumManagement(WebDriver driver) {
		super(driver);
		this._driver = driver;
	}

	WebDriver _driver = null;
	BaseClass _baseClass = new BaseClass();

	public boolean VerifyAddAddendumPage() throws UserException {
		if (_baseClass.verifyIsElementPresent(AddAddendumHeading, _driver))
			return true;
		else
			return false;
	}

	public void enter_AddendumDetails(String addendumName, String comment) throws UserException, InterruptedException {
		_baseClass.WaitForElementClickable(AddStipulation_Button, _driver);
		_baseClass.enterData(AddendumName, _driver, addendumName);
		_baseClass.enterData(AddendumComment, _driver, comment);
		_baseClass.ClickElement(AddStipulation_Button, _driver);
	}

	public void enterStipulationDetails(String stipulationDetails) throws UserException, InterruptedException {
		String[] _stipDetails = stipulationDetails.split(":");
		String description = _stipDetails[0];
		String category = _stipDetails[1];
		String stipulationType = _stipDetails[2];
		String applicableTo = _stipDetails[3];
		String effectiveFrom = _stipDetails[4];
		String effectiveTo = _stipDetails[5];
		_driver.switchTo().frame(Frame);
		_baseClass.enterData(StipulationDescription, _driver, description);
		_driver.switchTo().parentFrame();
		_baseClass.selectFromDropdown(_driver, StipulationCategory, category);
		_baseClass.selectFromDropdown(_driver, StipulationType, stipulationType);
		if (applicableTo.equals("Agreement")) {
			_baseClass.ClickElement(ApplicableTo_Agreement, _driver);
		} else {
			_baseClass.ClickElement(ApplicableTo_Office, _driver);
			_baseClass.ClickElement(SelectOfficesLink, _driver);
			_baseClass.ClickElement(SelectFirstOffice, _driver);
			_baseClass.ClickElement(SaveSelectedOffice, _driver);
		}
		if (effectiveFrom != "") {
			_baseClass.selectFromDropdown(_driver, EffectiveFrom, effectiveFrom);
			_baseClass.selectFromDropdown(_driver, EffectiveTo, effectiveTo);
		}

		_baseClass.ClickElement(SubmitStipulation, _driver);
	}

	public boolean verifyConfimrationPage() {
		try {
			_baseClass.verifyIsElementPresent(ConfirmationText, _driver);
			return true;
		} catch (UserException exception) {
			return false;
		}
	}

	public void confirmationPageNavigation(String _actionName) throws UserException {
		if (_actionName.equalsIgnoreCase("View Agreement Terms"))
			_baseClass.ClickElement(viewAgreementHyperLink, _driver);
		else if (_actionName.equalsIgnoreCase("Manage Agreement Terms"))
			_baseClass.ClickElement(manageAgreementHyperLink, _driver);
	}

	public boolean verifyManagePageHeader() throws UserException {
		if (_baseClass.verifyIsElementPresent(ManageBillingHeader, _driver))
			return true;
		else {
			return false;
		}
	}
}
