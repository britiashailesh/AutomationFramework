package com.aom.Methods;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import com.aom.pageObjects.AgreementHomePageObj;
import com.aom.support.BaseClass;
import com.aom.support.ScenarioContext;
import com.aom.support.UserException;
import com.aom.support.globalData.AgreementData;
import com.aom.support.objects.Agreement;

public class AgreementHomePage extends AgreementHomePageObj {
	public AgreementHomePage(WebDriver driver) {
		super(driver);
		this._driver = driver;
	}

	WebDriver _driver = null;
	BaseClass _baseClass = new BaseClass();

	public void ClickOnFirstGridRecord() throws UserException {
		_baseClass.ClickElement(gridTopRow, _driver);
	}

	public void NavigateToEnterStip_Alert(String _dealRefNum) throws UserException {
		_baseClass.ClickElement(pendingAddendumSetup_alert, _driver);
		grid_navigation("Enter Stipulation", _dealRefNum);
		_baseClass.WaitForPageLoad(_driver);
	}

	public void grid_navigation(String actionPanel_Option, String _dealRefNum) {
		try {
			int gridRowCount = gridRow.size();
			double searchResultCount = Double
					.parseDouble(AdvancedSearchResultCount.getText().split(":")[1].split(" ")[1]);
			int searchResultCountinPage = (int) Math.ceil((searchResultCount / 10));
			try {
				do {
					for (int i = 0; i < gridRowCount; i++) {
						if (gridRow.get(i).getText().equalsIgnoreCase(_dealRefNum)) {
							_baseClass.ClickElement(gridRow.get(i), _driver);
							actionPanelNavigation(actionPanel_Option);
							return;
						}
					}
					searchResultCountinPage--;
					if (searchResultCount > 0) {
						_baseClass.ClickElement(GridPaginationNextButton, _driver);
					}
				} while (searchResultCountinPage > 0);

			} catch (Exception e) {
				System.out.println("Deal Reference Number not found in grid : " + _dealRefNum);
			}
		} catch (Exception e) {
			System.out.println("Deal Reference Number not found in grid : " + _dealRefNum);
		}
	}

	public void actionPanelNavigation(String actionPanel_Option) throws UserException {
		if (actionPanel_Option.equalsIgnoreCase("Enter Stipulation")) {
			_baseClass.ClickElement(actionPanel_EnterStipulation, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("Addendum")) {
			_baseClass.ClickElement(actionPanel_Addendum, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("Manage Agreement Terms")) {
			_baseClass.ClickElement(actionPanel_Manage, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("Review & Approve")) {
			_baseClass.ClickElement(actionPanel_Review, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("View Agreement Terms")) {
			_baseClass.ClickElement(actionPanel_View, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("Enter IFF details")) {
			_baseClass.ClickElement(actionPanel_EnterIFF, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("Renewal")) {
			_baseClass.ClickElement(actionPanel_Renewal, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("Edit Addendum")) {
			_baseClass.ClickElement(EditAddendum, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("View Addendum")) {
			_baseClass.ClickElement(ViewAddendum, _driver);
			return;
		} else if (actionPanel_Option.equalsIgnoreCase("Delete")) {
			_baseClass.ClickElement(DeleteAddendum, _driver);
			return;
		}
		System.out.println("Action not found " + actionPanel_Option);
	}

	public boolean IsdashErrorThrown() throws UserException {
		return _baseClass.verifyElementNotPresent(dashError, _driver);
	}

	public Agreement setAgreementSceanrioContext(ResultSet _results) throws NumberFormatException, SQLException {
		while (_results.next()) {
			ScenarioContext.setAgreementContext(AgreementData.agreementID, _results.getString("AgreementId"));
			ScenarioContext.setAgreementContext(AgreementData.agreementGuid, _results.getString("agreementGuid"));
			ScenarioContext.setAgreementContext(AgreementData.rfgbrandKey, _results.getString("rfgbrandKey"));
			ScenarioContext.setAgreementContext(AgreementData.CompanyKey, _results.getString("CompanyKey"));
			ScenarioContext.setAgreementContext(AgreementData.agreementKey, _results.getString("agreementKey"));
		}
		Agreement _agreement = new Agreement(
				Integer.parseInt(ScenarioContext.getAgreementContext(AgreementData.agreementKey).toString()),
				Integer.parseInt(ScenarioContext.getAgreementContext(AgreementData.rfgbrandKey).toString()),
				Integer.parseInt(ScenarioContext.getAgreementContext(AgreementData.CompanyKey).toString()),
				ScenarioContext.getAgreementContext(AgreementData.agreementGuid).toString(),
				ScenarioContext.getAgreementContext(AgreementData.agreementID).toString());
		return _agreement;
	}

	public void quickSearch(String searchParameter) throws UserException {
		_baseClass.enterData(quicksearch, _driver, searchParameter);
		_baseClass.ClickElement(quicksearchList, _driver);
	}

	public boolean VerifyViewAgreementHeader() {
		try {
			return _baseClass.verifyIsElementPresent(ViewAgreementHeader, _driver);
		} catch (Exception E) {
			return false;
		}
	}

	public void NavigateToAgreementHomePage() throws UserException {
		_baseClass.ClickElement(AgreementTermsMenu, _driver);
	}

	
}
