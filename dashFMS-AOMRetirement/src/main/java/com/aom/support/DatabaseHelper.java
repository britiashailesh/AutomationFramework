package com.aom.support;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import com.aom.support.globalData.Common;
import com.aom.support.objects.Agreement;
import com.aom.support.objects.Brand;
import com.aom.support.objects.Deal;

public class DatabaseHelper {
	public static ResultSet _result = null;
	public static Connection connection = null;
	public static Statement _stmt = null;
	private DataGenerator _dataGen = new DataGenerator();

	public static void connectDB() throws Exception {
		String databaseName = GetProperty.getDBName();
		String serverName = GetProperty.getServerName();
		String dbUserName = GetProperty.getDBUserName();
		String dbPassword = GetProperty.getDBPassword();
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String connectionUrl = "jdbc:sqlserver://" + serverName + ";databaseName=" + databaseName + ";user="
				+ dbUserName + ";password=" + dbPassword;
		try {
			connection = DriverManager.getConnection(connectionUrl);
		} catch (SQLException e1) {
			throw new UserException("Unable to connect to database " + e1.getMessage());
		}
		try {
			_stmt = connection.createStatement();
		} catch (SQLException e) {
			throw new UserException("Unable to connect to database " + e.getMessage());
		}
	}

	public ResultSet getResultset(String query) throws Exception {
		connectDB();
		try {
			_result = _stmt.executeQuery(query);
		} catch (SQLException sql) {
			throw new UserException("Sql Exception encountered. Unable to get results");
		}
		return _result;
	}

	public String getString(String query, String _columnName) throws Exception {
		connectDB();
		String _toReturn = "";
		try {
			_result = _stmt.executeQuery(query);
			while (_result.next()) {
				_toReturn = _result.getString(_columnName);
			}
		} catch (SQLException sql) {
			throw new UserException("Sql Exception encountered. Unable to get results");
		}
		return _toReturn;
	}

	public void ExecuteQuery(String _query) throws Exception {
		connectDB();
		try {
			_stmt.execute(_query);
		} catch (Exception E) {
			throw new UserException("Error executing query");
		}
	}

	public String getNewGuid() throws Exception {
		connectDB();
		String newId = null;
		try {
			_result = _stmt.executeQuery("Select Newid() newGuid");
			while (_result.next()) {
				newId = _result.getString("newGuid");
			}
		} catch (SQLException sql) {
			throw new UserException("Sql Exception encountered. Unable to get results");
		}
		return newId;
	}

	public Brand getBrandDetails(int _brandKey) {
		switch (_brandKey) {

		case 1:
			Brand _brand1 = new Brand(1, "BHG", "6E20F36B-6522-41C3-B1E6-33B7F97FD40F");
			return _brand1;

		case 2:
			Brand _brand2 = new Brand(2, "C21", "FA13194C-1A74-49C7-B454-D1CFEF45EE40");
			return _brand2;

		case 3:
			Brand _brand3 = new Brand(3, "CB", "3C4FE27A-A4B3-40BC-A0B1-EFBC4CF2F70F");
			return _brand3;

		case 4:
			Brand _brand4 = new Brand(4, "CBC", "15C3D33E-1CDF-4235-824A-AA4C7FDA641C");
			return _brand4;

		case 5:
			Brand _brand5 = new Brand(5, "ERA", "1D7DE1EC-4CE7-4517-BB71-582C9046555D");
			return _brand5;

		case 6:
			Brand _brand6 = new Brand(6, "SIR", "CD4FA225-87A6-4F3A-AED3-13F567CD590E");
			return _brand6;

		case 7:
			Brand _brand7 = new Brand(10, "COR", "242B2D64-0CC7-E811-9977-FC15B40D8594");
			return _brand7;
		}
		return null;
	}

	public String getCurrentDate() {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
		LocalDateTime now = LocalDateTime.now();
		return (dtf.format(now));
	}

	public String[] getDealType(int _dealTypeKey, int _dealSubTypeKey) throws Exception {
		String _query1 = " Select CodeDescription from Global.Code where Codekey = " + _dealTypeKey;
		String _query2 = " Select CodeDescription from Global.Code where Codekey = " + _dealSubTypeKey;
		String _dealType = getString(_query1, "CodeDescription");
		String _dealSubType = getString(_query2, "CodeDescription");
		String _toReturn[] = { _dealType, _dealSubType };
		return (_toReturn);
	}

	public String getCompanyName(int _companyKey) throws Exception {
		connectDB();
		String _query = "Select CompanyName from brokerage.companyi18n where companyKey = " + _companyKey;
		return (getString(_query, "CompanyName"));
	}

	public String getExistingOfficeId(String _agreementGuid) throws Exception {
		String _officeId = null;
		String _query1 = "Select CompanyKey from Billingcentral.agreement where agreementGuid= '" + _agreementGuid
				+ "'";
		try {
			String _compKey = getString(_query1, "CompanyKey");
			int _companyKey = Integer.parseInt(_compKey);
			String _query2 = "Select top 1 OfficeId from Brokerage.office where statuskey = 937 and CompanyKey = "
					+ _companyKey + " order by newId()";
			_officeId = getString(_query2, "OfficeId");
		} catch (Exception E) {
			throw new UserException("Error getting office Id");
		}
		return _officeId;
	}

	public String TriggerAddendumPendingAlert(Agreement _agreement, int _dealTypeKey, int _dealSubTypeKey)
			throws Exception {
		Brand _brand = getBrandDetails(_agreement.getRfgBrandKey());
		String _dealRefNumb = _dataGen.getDealReferenceNumber(_brand.getBrandCode());
		String _dealGuid = getNewGuid();
		String _companyName = getCompanyName(_agreement.getCompanyKey());
		String _executionDate = getCurrentDate();
		Deal _deal = new Deal(_agreement.getAgreementGuid(), _brand.getBrandGuid(), _dealGuid, _dealRefNumb,
				_companyName, _executionDate, _dealTypeKey, _dealSubTypeKey);
		if (_dealTypeKey == 5268) {
			// call method to execute Branch Deal
			createBranchDeal(_deal, _brand.getBrandCode(), _companyName);
		} else {
			// call method to execute Generic Deal
			CreateGenericDeal(_deal);
		}
		return _dealRefNumb;
	}

	public String CreateNewAgreement(int _brandKey, int _dealTypeKey, int _dealSubTypeKey, String _companyName)
			throws Exception {
		Brand _brand = getBrandDetails(_brandKey);
		String _dealRefNumb = _dataGen.getDealReferenceNumber(_brand.getBrandCode());
		String _dealGuid = getNewGuid();
		String _agreementGuid = getNewGuid();
		String _executionDate = getCurrentDate();
		Deal _deal = new Deal(_agreementGuid, _brand.getBrandGuid(), _dealGuid, _dealRefNumb, _companyName,
				_executionDate, _dealTypeKey, _dealSubTypeKey);
		String _locationGuid = getNewGuid();
		String _officeId = getNewOfficeId(_deal.get_dealGuid(), _locationGuid, _brand.getBrandCode(), _companyName,
				_agreementGuid);
		String _metaData = prepareCommonXml(_deal, _officeId, _locationGuid, _agreementGuid);
		String[] _dealType = getDealType(_deal.get_dealTypeKey(), _deal.get_dealSubTypeKey());
		String _query4 = "exec [Integration].[ContractualEventSave] \r\n" + "       @AgreementGuid = '" + _agreementGuid
				+ "', \r\n" + "       @BrandGuid = '" + _deal.get_brandGuid() + "', \r\n" + "       @DealGuid = '"
				+ _deal.get_dealGuid() + "', \r\n" + "       @DealExecutiondate = '" + _deal.get_executionDate()
				+ "', \r\n" + "       @Dealreferencenumber = '" + _deal.get_dealRefNumb() + "', \r\n"
				+ "       @DealStatus = 'Active', \r\n" + "       @DealSubtype = '" + _dealType[1] + "', \r\n"
				+ "       @DealType = '" + _dealType[0] + "', \r\n"
				+ "       @Franchiseguid = '46bbb08f-1a27-df11-883a-00215ec40f66', \r\n" + "       @MetaData = "
				+ _metaData + ", \r\n"
				+ "       @DocuSignURL = 'https://realogy.sharepoint.com/sites/rfgdynamicsdev04/real_contract/', \r\n"
				+ "       @IdentityGuid = '925CB2AB-7FF0-4A00-89A6-6AE63803E815'";
		ExecuteQuery(_query4);
		return _dealRefNumb;
	}

	public String getNewOfficeId(String _dealGuid, String _locationGuid, String _brandCode, String _companyName,
			String _agreementGuid) throws Exception {
		connectDB();
		String _query1 = "declare @p1 Security.ExecutionContext_TVP\r\n"
				+ "insert into @p1 values(88,30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)";
		String _query2 = "declare @p8 Integration.KeyDispenser_View_TVP\r\n" + "\r\n"
				+ "exec Integration.CompanyOfficeIDDispense \r\n" + "@ExecutionContext=@p1,\r\n" + "@BrandCode=N'"
				+ _brandCode + "',\r\n" + "@CountryCode=N'US',\r\n" + "@DealGuid='";
		String _query3 = _dealGuid + "',\r\n" + "@AgreementGuid='" + _agreementGuid + "',\r\n"
				+ "@AgreementCompanyName=N'" + _companyName + "' ,@MainLocationGuid='" + _locationGuid + "' ,\r\n"
				+ "@KeyDispenserType=@p8 ";
		String _query = _query1 + _query2 + _query3;
		System.out.println(_query);
		ExecuteQuery(_query);
		String _getOfficeQuery = "select IdDispensed from integration.DealIdDispenserLog where EntityIdTypeKey = 14 and dealGuid = '"
				+ _dealGuid + "'";
		String _officeId = getString(_getOfficeQuery, "IdDispensed");
		return _officeId;
	}

	public void createBranchDeal(Deal _deal, String _brandCode, String _companyName) throws Exception {
		String _locationGuid = getNewGuid();
		String _officeId = getBranchOfficeId(_deal, _locationGuid, _brandCode, _companyName);
		String _metaData = prepareCommonXml(_deal, _officeId, _locationGuid, null);
		String[] _dealType = getDealType(_deal.get_dealTypeKey(), _deal.get_dealSubTypeKey());
		String _query4 = "exec [Integration].[ContractualEventSave] \r\n" + "       @AgreementGuid = '"
				+ _deal.get_agreementGuid() + "', \r\n" + "       @BrandGuid = '" + _deal.get_brandGuid() + "', \r\n"
				+ "       @DealGuid = '" + _deal.get_dealGuid() + "', \r\n" + "       @DealExecutiondate = '"
				+ _deal.get_executionDate() + "', \r\n" + "       @Dealreferencenumber = '" + _deal.get_dealRefNumb()
				+ "', \r\n" + "       @DealStatus = 'Active', \r\n" + "       @DealSubtype = '" + _dealType[1]
				+ "', \r\n" + "       @DealType = '" + _dealType[0] + "', \r\n"
				+ "       @Franchiseguid = '46bbb08f-1a27-df11-883a-00215ec40f66', \r\n" + "       @MetaData = "
				+ _metaData + ", \r\n"
				+ "       @DocuSignURL = 'https://realogy.sharepoint.com/sites/rfgdynamicsdev04/real_contract/', \r\n"
				+ "       @IdentityGuid = '925CB2AB-7FF0-4A00-89A6-6AE63803E815'";
		ExecuteQuery(_query4);
	}

	public String getBranchOfficeId(Deal _deal, String _locationGuid, String _brandCode, String _companyName)
			throws Exception {
		connectDB();
		String _query1 = "declare @p1 Security.ExecutionContext_TVP\r\n"
				+ "insert into @p1 values(88,30,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL)";
		String _query2 = "declare @p8 Integration.KeyDispenser_View_TVP\r\n"
				+ "				insert into @p8 values(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'"
				+ _locationGuid + "',NULL,NULL,NULL,NULL)" + "exec Integration.CompanyOfficeIDDispense \r\n"
				+ "@ExecutionContext=@p1,\r\n" + "@BrandCode=N'" + _brandCode + "',\r\n" + "@CountryCode=N'US',\r\n"
				+ "@DealGuid='";
		String _query3 = _deal.get_dealGuid() + "',\r\n" + "@AgreementGuid='" + _deal.get_agreementGuid() + "',\r\n"
				+ "@AgreementCompanyName=N'" + _companyName + "',\r\n" + "@KeyDispenserType=@p8 ";
		String _query = _query1 + _query2 + _query3;
		System.out.println(_query);
		System.out.println("------------------");
		ExecuteQuery(_query);
		String _getOfficeQuery = "select IdDispensed from integration.DealIdDispenserLog where EntityIdTypeKey = 2 and dealGuid = '"
				+ _deal.get_dealGuid() + "'";
		String _officeId = getString(_getOfficeQuery, "IdDispensed");
		return _officeId;
	}

	public String getAddress() {
		String[] Address = { "160 Ayer Road/Harvard/MA/01451", "Corporate Office/Zapopan/JAL/45040",
				"1 Bronzepointe, Ste 2A/Belleville/IL/62226", "200 7th Ave/Brooklyn/NY/11215",
				"1991 Apex Peakway/Apex/NC/27502", "114 Early Blvd/Brownwood/TX/76801",
				"3440 Toringdon Way Ste 110/Charlotte/NC/28277", "1300 E. 15th Street; Suite 130/Edmond/OK/73013",
				"620 Lakeview Road/Clearwater/FL/34616", "1906 Treble Drive/Humble/TX/77338" };
		Random rand = new Random();
		int numb = rand.nextInt(10);
		return Address[numb];
	}

	public void CreateGenericDeal(Deal _deal) throws Exception {
		String _officeId = getExistingOfficeId(_deal.get_agreementGuid());
		String _metaData = prepareCommonXml(_deal, _officeId, null, null);
		String[] _dealTypes = getDealType(_deal.get_dealTypeKey(), _deal.get_dealSubTypeKey());
		String _query = "exec [Integration].[ContractualEventSave] \r\n" + "       @AgreementGuid = '"
				+ _deal.get_agreementGuid() + "', \r\n" + "       @BrandGuid = '" + _deal.get_brandGuid() + "', \r\n"
				+ "       @DealGuid = '" + _deal.get_dealGuid() + "', \r\n" + "       @DealExecutiondate = '"
				+ _deal.get_executionDate() + "', \r\n" + "       @Dealreferencenumber = '" + _deal.get_dealRefNumb()
				+ "', \r\n" + "       @DealStatus = 'Active', \r\n" + " @DealSubtype = '" + _dealTypes[1] + "', \r\n"
				+ "       @DealType = '" + _dealTypes[0] + "', \r\n"
				+ "       @Franchiseguid = '46bbb08f-1a27-df11-883a-00215ec40f66', \r\n" + "       @MetaData = "
				+ _metaData + ", \r\n"
				+ "       @DocuSignURL = 'https://realogy.sharepoint.com/sites/rfgdynamicsdev04/real_contract/', \r\n"
				+ "       @IdentityGuid = '925CB2AB-7FF0-4A00-89A6-6AE63803E815'";
		ExecuteQuery(_query);
	}

	public String prepareCommonXml(Deal _deal, String _officeId, String _locationGuid, String _agreementGuid)
			throws Exception {
		String[] _dealTypes = getDealType(_deal.get_dealTypeKey(), _deal.get_dealSubTypeKey());
		String _xml1 = "'<Deal><dealdetailedinfo>\r\n" + " <agreementGuid>" + _deal.get_agreementGuid()
				+ "</agreementGuid>";
		String _xml2 = "<brandguid>" + _deal.get_brandGuid() + "</brandguid>";
		String _xml3 = "<dealGuid>" + _deal.get_dealGuid() + "</dealGuid>";
		String _xml4 = "<dealexecutiondate>" + _deal.get_executionDate() + "</dealexecutiondate>\r\n"
				+ " <dealreferncenumber>" + _deal.get_dealRefNumb() + "</dealreferncenumber>";
		String _xml5 = "<dealstatus>Active</dealstatus>\r\n" + "  <dealsubtype>" + _dealTypes[1] + "</dealsubtype>\r\n"
				+ "  <dealtype>" + _dealTypes[0] + "</dealtype>\r\n" + "  <docusignlink />\r\n"
				+ "  <franchiseguid></franchiseguid>\r\n" + "</dealdetailedinfo>\r\n" + "<dealrequestinfo>\r\n"
				+ "  <companyguid></companyguid>\r\n" + "  <prospectname></prospectname>\r\n"
				+ "  <targetcompanyguid />\r\n" + "</dealrequestinfo>";
		String address = getAddress();
		if (_deal.get_dealTypeKey() == 5266) {
			ScenarioContext.setCommonContext(Common.NewAddress, address);
		}
		String _xml6 = "<offices><office>\r\n" + "<addressline1>" + address.split("/")[0] + "</addressline1>\r\n"
				+ "<addressline2 />\r\n" + "<city>" + address.split("/")[1] + "</city>\r\n" + "<companyid />\r\n"
				+ "<country>United States</country>\r\n"
				+ "<currentofficeguid /><dbaname>Aspen Snowmass SIR</dbaname>\r\n" + "<locationguid>" + _locationGuid
				+ "</locationguid><officeid>" + _officeId + "</officeid>\r\n" + "<postalcode>" + address.split("/")[3]
				+ "</postalcode>\r\n" + "<officetype>Main office</officetype>\r\n"
				+ "<state>Texas</state></office></offices></Deal>'";
		String _xml = _xml1 + _xml2 + _xml3 + _xml4 + _xml5 + _xml6;
		// System.out.println(_xml);
		return _xml;
	}
}
