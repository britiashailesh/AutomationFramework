package com.aom.support;

import org.openqa.selenium.WebDriver;

import com.aom.Methods.AddendumManagement;
import com.aom.Methods.AgreementHomePage;
import com.aom.Methods.Login;
import com.aom.Methods.ManageAgreement;
import com.aom.Methods.TradeName;
import com.aom.Methods.ViewAgreementTerms;

public class PageObjectManager {
	private WebDriver _driver;
	private Login _login;
	private AgreementHomePage _agreementHomePage;
	private TradeName _tradeName;
	private AddendumManagement _addendumManagement;
	private ManageAgreement _manageAgreement;
	private ViewAgreementTerms _viewAgreementTerms;

	public PageObjectManager(WebDriver driver) {
		this._driver = driver;
	}

	public Login getLoginMethod() {
		return (_login == null) ? _login = new Login(_driver) : _login;
	}

	public AgreementHomePage getAgreementHomePage() {
		return (_agreementHomePage == null) ? _agreementHomePage = new AgreementHomePage(_driver) : _agreementHomePage;
	}

	public AddendumManagement getAddendumManagement() {
		return (_addendumManagement == null) ? _addendumManagement = new AddendumManagement(_driver)
				: _addendumManagement;
	}

	public TradeName getTradeName() {
		return (_tradeName == null) ? _tradeName = new TradeName(_driver) : _tradeName;
	}

	public ManageAgreement getManageAgreement() {
		return (_manageAgreement == null) ? _manageAgreement = new ManageAgreement(_driver) : _manageAgreement;
	}

	public ViewAgreementTerms getViewAgreement() {
		return (_viewAgreementTerms == null) ? _viewAgreementTerms = new ViewAgreementTerms(_driver) : _viewAgreementTerms;
	}

}
