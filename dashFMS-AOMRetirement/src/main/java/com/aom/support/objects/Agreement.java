package com.aom.support.objects;

public class Agreement {
	private int agreementKey, rfgBrandKey, companyKey;
	private String agreementGuid, agreementId;

	public int getAgreementKey() {
		return agreementKey;
	}

	public int getRfgBrandKey() {
		return rfgBrandKey;
	}

	public int getCompanyKey() {
		return companyKey;
	}

	public String getAgreementGuid() {
		return agreementGuid;
	}

	public String getAgreementId() {
		return agreementId;
	}

	public void setAgreementKey(int agreementKey) {
		this.agreementKey = agreementKey;
	}

	public void setRfgBrandKey(int rfgBrandKey) {
		this.rfgBrandKey = rfgBrandKey;
	}

	public void setCompanyKey(int companyKey) {
		this.companyKey = companyKey;
	}

	public void setAgreementGuid(String agreementGuid) {
		this.agreementGuid = agreementGuid;
	}

	public void setAgreementId(String agreementId) {
		this.agreementId = agreementId;
	}

	public Agreement(int agreementKey, int rfgBrandKey, int companyKey, String agreementGuid, String agreementId) {
		super();
		this.agreementKey = agreementKey;
		this.rfgBrandKey = rfgBrandKey;
		this.companyKey = companyKey;
		this.agreementGuid = agreementGuid;
		this.agreementId = agreementId;
	}

}
