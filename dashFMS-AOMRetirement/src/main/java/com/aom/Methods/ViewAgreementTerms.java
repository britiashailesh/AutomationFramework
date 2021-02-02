package com.aom.Methods;

import org.openqa.selenium.WebDriver;

import com.aom.pageObjects.ViewAgreementTermsObj;
import com.aom.support.BaseClass;
import com.aom.support.DatabaseHelper;
import com.aom.support.GetProperty;

public class ViewAgreementTerms extends ViewAgreementTermsObj {
	WebDriver _driver = null;
	BaseClass _baseClass = new BaseClass();
	DatabaseHelper _dbHelp = new DatabaseHelper();

	public ViewAgreementTerms(WebDriver driver) {
		super(driver);
		this._driver = driver;
	}

	public boolean VerifyTradeName(int _agreementKey) throws Exception {
		String _query = GetProperty.getQuery("getTradeName") + _agreementKey;
		String _TradeNameDb = _dbHelp.getString(_query, "TradeName");
		String _TradeNameUi = TradeName.getText();
		return _TradeNameDb.equalsIgnoreCase(_TradeNameUi);
	}

}
