package com.aom.stepDefinition;

import static org.junit.Assert.assertTrue;

import java.sql.ResultSet;

import com.aom.Methods.AgreementHomePage;
import com.aom.support.DatabaseHelper;
import com.aom.support.GetProperty;
import com.aom.support.ScenarioContext;
import com.aom.support.TestContext;
import com.aom.support.globalData.Common;
import com.aom.support.objects.Agreement;

import cucumber.api.java.en.Given;

public class test1 {
	TestContext _testContext;
	AgreementHomePage _agreementMethod;
	DatabaseHelper _dbHelp = new DatabaseHelper();
	ResultSet _results;
	Agreement _agreement;

	public test1(TestContext _testContext) {
		_agreementMethod = _testContext.getPageObjectManager().getAgreementHomePage();
	}

	@Given("^I do something$")
	public void i_do_something() throws Throwable {
		String query = GetProperty.getQuery("getApprovedAgreement") + " order by newId()";
		try {
			_results = _dbHelp.getResultset(query);
			_agreement = _agreementMethod.setAgreementSceanrioContext(_results);
			_dbHelp.TriggerAddendumPendingAlert(_agreement, 5269, 5284); // Returns Deal Reference Number
			_dbHelp.TriggerAddendumPendingAlert(_agreement, 5268, 5283);
			_dbHelp.CreateNewAgreement(6, 5272, 5292, "test First Company");
		} catch (Exception e1) {
			assertTrue("Error " + e1.toString(), false);
		}
	}

}
