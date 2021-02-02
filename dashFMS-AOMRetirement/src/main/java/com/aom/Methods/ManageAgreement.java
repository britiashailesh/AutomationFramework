package com.aom.Methods;

import org.openqa.selenium.WebDriver;

import com.aom.pageObjects.ManageAgreementObj;
import com.aom.support.BaseClass;
import com.aom.support.UserException;

public class ManageAgreement extends ManageAgreementObj {
	WebDriver _driver = null;
	BaseClass _baseClass = new BaseClass();

	public ManageAgreement(WebDriver driver) {
		super(driver);
		this._driver = driver;
	}

	public String getCompanyName() {
		return CompanyName.getText();
	}

	public void EnterCommentsAndSubmit() throws UserException {
		_baseClass.enterData(CommentsSection, _driver, "Submitted Terms");
		_baseClass.ClickElement(Submit_Button, _driver);
		_baseClass.ClickElement(button_popUpYes, _driver);
	}

	public boolean verifyConfirmationMessage() throws UserException {
		if (_baseClass.verifyIsElementPresent(ConfirmationText, _driver)) {
			return true;
		}
		return false;
	}

}
