package com.aom.support.objects;

public class Brand {
	private int brandKey;
	private String brandCode, BrandGuid;

	public Brand(int brandKey, String brandCode, String brandGuid) {
		this.brandKey = brandKey;
		this.brandCode = brandCode;
		BrandGuid = brandGuid;
	}

	public int getBrandKey() {
		return brandKey;
	}

	public void setBrandKey(int brandKey) {
		this.brandKey = brandKey;
	}

	public String getBrandCode() {
		return brandCode;
	}

	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}

	public String getBrandGuid() {
		return BrandGuid;
	}

	public void setBrandGuid(String branduid) {
		BrandGuid = branduid;
	}

}
