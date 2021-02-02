package com.aom.support;

import com.aom.support.WebDriverHelper;

public class TestContext {
	private WebDriverHelper _webDriverHelper;
	private PageObjectManager pageObjectManager;

	public TestContext() {
		_webDriverHelper = new WebDriverHelper();
		pageObjectManager = new PageObjectManager(_webDriverHelper.getDriver());
	}

	public WebDriverHelper getWebDriverManager() {
		return _webDriverHelper;
	}

	public PageObjectManager getPageObjectManager() {
		return pageObjectManager;
	}

	
}
