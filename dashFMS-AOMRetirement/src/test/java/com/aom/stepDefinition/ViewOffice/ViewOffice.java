package com.aom.stepDefinition.ViewOffice;

import static org.junit.Assert.assertTrue;
import java.sql.ResultSet;
import com.aom.Methods.*;
import com.aom.support.*;
import com.aom.support.globalData.*;
import com.aom.support.objects.Agreement;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ViewOffice {
	TestContext _testContext;
	AgreementHomePage _agreementMethod;
	DatabaseHelper _dbHelp = new DatabaseHelper();
	ResultSet _results;
	Agreement _agreement;
	TradeName _tradeNameMethod;
	AddendumManagement _addendumManagement;
	ManageAgreement _manageAgreement;
	ViewAgreementTerms _viewAgreementTerms;

	public ViewOffice(TestContext _testContext) {
		_agreementMethod = _testContext.getPageObjectManager().getAgreementHomePage();
		_tradeNameMethod = _testContext.getPageObjectManager().getTradeName();
		_manageAgreement = _testContext.getPageObjectManager().getManageAgreement();
		_addendumManagement = _testContext.getPageObjectManager().getAddendumManagement();
		_viewAgreementTerms = _testContext.getPageObjectManager().getViewAgreement();
	}

	@Given("^I navigated to View Agreement Terms screen$")
	public void i_navigated_to_View_Agreement_Terms_screen() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^I click of an office in the office grid$")
	public void i_click_of_an_office_in_the_office_grid() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@When("^I click on View Office action in the action tab$")
	public void i_click_on_View_Office_action_in_the_action_tab() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}

	@Then("^I should be on the view office page$")
	public void i_should_be_on_the_view_office_page() throws Throwable {
	    // Write code here that turns the phrase above into concrete actions
	}
}
