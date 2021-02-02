package com.aom.Methods;

import java.sql.ResultSet;
import java.util.Arrays;

import org.openqa.selenium.WebDriver;

import com.aom.pageObjects.TradeNameObj;
import com.aom.support.*;
import com.aom.support.objects.*;

public class TradeName extends TradeNameObj {
	WebDriver _driver;
	BaseClass _baseClass = new BaseClass();

	public TradeName(WebDriver driver) {
		super(driver);
		this._driver = driver;
	}

	private DatabaseHelper _dbHelp = new DatabaseHelper();
	
	public String ManageTradeName(String _applicableTo, int rfgBrandKey) throws UserException {
		ManageAgreement _manageAgreement = new ManageAgreement(_driver);
		String _companyName = _manageAgreement.getCompanyName();
		String _tradeName = "";
		switch (rfgBrandKey) {
		case 1:
			_tradeName = _companyName + "Better Homes and Gardens";
			break;
		case 6:
			_tradeName = _companyName + "Sotheby's International Realty";
			break;
		case 5:
			_tradeName = _companyName + "ERA";
			break;
		case 3:
			_tradeName = _companyName + "Coldwell Banker";
			break;
		case 4:
			_tradeName = _companyName + "Coldwell Banker Commerical";
			break;
		case 2:
			_tradeName = _companyName + "Century 21";
			break;
		case 10:
			_tradeName = _companyName + "Corcoran";
			break;

		}
		if (_applicableTo.contentEquals("Agreement")) {
			_baseClass.enterData(ManageTradeNameAgreement, _driver, _tradeName);
		} else
			_baseClass.enterData(ManageTradeNameOffice, _driver, _tradeName);
		return _tradeName;
	}

	public boolean verifyTradeName(String _enteredTradeName, int _agreementKey) throws Exception {
		String _query = "select TradeName from BillingCentral.AgreementTradeName where isActive = 1 and AgreementKey = "
				+ _agreementKey;
		ResultSet _result = _dbHelp.getResultset(_query);
		String[] _dbTradeNames = new String[_result.getFetchSize()];
		int i = 0;
		while (_result.next()) {
			_dbTradeNames[i] = _result.getString("TradeName");
			i++;
		}
		return (Arrays.stream(_dbTradeNames).anyMatch(_enteredTradeName::equals));

	}
}
