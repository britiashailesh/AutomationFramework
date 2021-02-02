package com.aom.support.objects;

public class Deal {
	private String _agreementGuid, _brandGuid, _dealGuid, _dealRefNumb, _companyName, _executionDate;
	private int _dealTypeKey, _dealSubTypeKey;

	public Deal(String _agreementGuid, String _brandGuid, String _dealGuid, String _dealRefNumb, String _companyName,
			String _executionDate, int _dealTypeKey, int _dealSubTypeKey) {
		this._agreementGuid = _agreementGuid;
		this._brandGuid = _brandGuid;
		this._dealGuid = _dealGuid;
		this._dealRefNumb = _dealRefNumb;
		this._companyName = _companyName;
		this._executionDate = _executionDate;
		this._dealTypeKey = _dealTypeKey;
		this._dealSubTypeKey = _dealSubTypeKey;
	}

	public String get_companyName() {
		return _companyName;
	}

	public void set_companyName(String _companyName) {
		this._companyName = _companyName;
	}

	public String get_executionDate() {
		return _executionDate;
	}

	public void set_executionDate(String _executionDate) {
		this._executionDate = _executionDate;
	}

	public String get_agreementGuid() {
		return _agreementGuid;
	}

	public void set_agreementGuid(String _agreementGuid) {
		this._agreementGuid = _agreementGuid;
	}

	public String get_brandGuid() {
		return _brandGuid;
	}

	public void set_brandGuid(String _brandGuid) {
		this._brandGuid = _brandGuid;
	}

	public String get_dealGuid() {
		return _dealGuid;
	}

	public void set_dealGuid(String _dealGuid) {
		this._dealGuid = _dealGuid;
	}

	public String get_dealRefNumb() {
		return _dealRefNumb;
	}

	public void set_dealRefNumb(String _dealRefNumb) {
		this._dealRefNumb = _dealRefNumb;
	}

	public int get_dealTypeKey() {
		return _dealTypeKey;
	}

	public void set_dealTypeKey(int _dealTypeKey) {
		this._dealTypeKey = _dealTypeKey;
	}

	public int get_dealSubTypeKey() {
		return _dealSubTypeKey;
	}

	public void set_dealSubTypeKey(int _dealSubTypeKey) {
		this._dealSubTypeKey = _dealSubTypeKey;
	}

}
