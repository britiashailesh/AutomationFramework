package com.aom.stepDefinition.TradeName;

import static org.junit.Assert.assertTrue;
import java.sql.ResultSet;
import com.aom.Methods.*;
import com.aom.support.*;
import com.aom.support.globalData.*;
import com.aom.support.objects.Agreement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class TradeNameSetup {
	TestContext _testContext;
	AgreementHomePage _agreementMethod;
	DatabaseHelper _dbHelp = new DatabaseHelper();
	ResultSet _results;
	Agreement _agreement;
	TradeName _tradeNameMethod;
	AddendumManagement _addendumManagement;
	ManageAgreement _manageAgreement;
	ViewAgreementTerms _viewAgreementTerms;

	public TradeNameSetup(TestContext _testContext) {
		_agreementMethod = _testContext.getPageObjectManager().getAgreementHomePage();
		_tradeNameMethod = _testContext.getPageObjectManager().getTradeName();
		_manageAgreement = _testContext.getPageObjectManager().getManageAgreement();
		_addendumManagement = _testContext.getPageObjectManager().getAddendumManagement();
		_viewAgreementTerms = _testContext.getPageObjectManager().getViewAgreement();
	}

	@Given("^I enter stipulation details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enterStipulationDetails(String _addendumName, String _stipulationDetails, int _dealType,
			int _dealSubType) {
		String query = GetProperty.getQuery("getApprovedAgreement") + " order by newId()";
		try {
			_results = _dbHelp.getResultset(query);
			_agreement = _agreementMethod.setAgreementSceanrioContext(_results);

			ScenarioContext.setCommonContext(Common.dealRefNum,
					_dbHelp.TriggerAddendumPendingAlert(_agreement, _dealType, _dealSubType));
			_agreementMethod.NavigateToAgreementHomePage();
			_agreementMethod.NavigateToEnterStip_Alert(ScenarioContext.getCommonContext(Common.dealRefNum).toString());
			// _agreementMethod.NavigateToEnterStip_Alert("C21-275184976");
			_addendumManagement.enter_AddendumDetails(_addendumName, "Comments");
			_addendumManagement.enterStipulationDetails(_stipulationDetails);
			assertTrue(_addendumManagement.verifyConfimrationPage());

		} catch (Exception e) {
			try { // method to retry
				if (_agreementMethod.IsdashErrorThrown()) {
					enterStipulationDetails("Manage Trade Name",
							"Manage Trade Name:Franchisee Information:DBA:Office:Term Start/ Office Open Date:Term End Date",
							_dealType, _dealSubType);
				}
			} catch (Exception e1) {
				assertTrue("Unable to enter stipulation " + e.toString(), false);
			}
		}
	}

	@Given("^I navigate to Manage Agreement Terms screen$")
	public void NavigateToManageAgreement() throws Throwable {
		try {
			_addendumManagement.confirmationPageNavigation("Manage Agreement Terms");
			assertTrue(_addendumManagement.verifyManagePageHeader());
		} catch (Exception e) {
			try {
				// method to retry
				if (_agreementMethod.IsdashErrorThrown()) {
					NavigateToManageAgreement();
				}
			} catch (Exception e1) {
				assertTrue(e.toString(), false);
			}
		}

	}

	@When("^I enter details and submit for approval \"([^\"]*)\"$")
	public void ManageTradeName(String _applicableTo) {
		try {
			String _enteredTradeName = _tradeNameMethod.ManageTradeName(_applicableTo,
					Integer.parseInt(ScenarioContext.getAgreementContext(AgreementData.rfgbrandKey).toString()));
			ScenarioContext.setCommonContext(Common.TradeName, _enteredTradeName);
			_manageAgreement.EnterCommentsAndSubmit();
			assertTrue(_manageAgreement.verifyConfirmationMessage());
		} catch (Exception e) {
			try {
				// method to retry
				if (_agreementMethod.IsdashErrorThrown()) {
					ManageTradeName(_applicableTo);
				}
			} catch (Exception e1) {
				assertTrue(e.toString(), false);
			}
		}
	}

	@Then("^Trade Name shall be updated$")
	public void VerifyTradeNameInDB() {
		try {
			assertTrue(_tradeNameMethod.verifyTradeName(ScenarioContext.getCommonContext(Common.TradeName).toString(),
					Integer.parseInt(ScenarioContext.getAgreementContext(AgreementData.agreementKey).toString())));
		} catch (Exception e) {
			assertTrue(e.getMessage(), false);
		}
	}

	@Given("^I navigate to View Agreement Terms screen$")
	public void QuickSearchToViewAgreement() throws Throwable {
		_agreementMethod.NavigateToAgreementHomePage();
		String query = GetProperty.getQuery("getApprovedAgreement") + " order by newId()";
		try {
			_results = _dbHelp.getResultset(query);
			_agreement = _agreementMethod.setAgreementSceanrioContext(_results);
			_agreementMethod.quickSearch(_agreement.getAgreementId());
			assertTrue(_agreementMethod.VerifyViewAgreementHeader());
		} catch (Exception e) {
			try {
				// method to retry
				if (_agreementMethod.IsdashErrorThrown()) {
					QuickSearchToViewAgreement();
				}
			} catch (Exception e1) {
				assertTrue(e.toString(), false);
			}
		}
	}

	@Then("^I should be able to see the Trade Name details$")
	public void VerifyTradeNameinView() throws Throwable {
		int agreementKey = Integer.parseInt(ScenarioContext.getAgreementContext(AgreementData.agreementKey).toString());
		assertTrue(_viewAgreementTerms.VerifyTradeName(agreementKey));
	}

	@Given("^I enter adhoc stipulation details \"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\",\"([^\"]*)\"$")
	public void enterAdhocStipulationDetails(String _addendumName, String _stipulationDetails, int _dealType,
			int _dealSubType) {
		String query = GetProperty.getQuery("getApprovedAgreement") + " order by newId()";
		try {
			_results = _dbHelp.getResultset(query);
			_agreement = _agreementMethod.setAgreementSceanrioContext(_results);
			_agreementMethod.NavigateToAgreementHomePage();
			//_agreementMethod.NavigateToAdhoc(ScenarioContext.getAgreementContext(AgreementData.agreementID).toString());
			_addendumManagement.enter_AddendumDetails(_addendumName, "Comments");
			_addendumManagement.enterStipulationDetails(_stipulationDetails);
			assertTrue(_addendumManagement.verifyConfimrationPage());

		} catch (Exception e) {
			try { // method to retry
				if (_agreementMethod.IsdashErrorThrown()) {
					enterStipulationDetails("Manage Trade Name",
							"Manage Trade Name:Franchisee Information:DBA:Office:Term Start/ Office Open Date:Term End Date",
							_dealType, _dealSubType);
				}
			} catch (Exception e1) {
				assertTrue("Unable to enter stipulation " + e.toString(), false);
			}
		}
	}
}
