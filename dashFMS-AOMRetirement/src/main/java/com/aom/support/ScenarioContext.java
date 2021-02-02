package com.aom.support;

import java.util.HashMap;
import java.util.Map;
import com.aom.support.globalData.AgreementData;
import com.aom.support.globalData.Common;

public class ScenarioContext {
	private static Map<String, Object> scenarioContext = new HashMap<>();

	public static void setAgreementContext(AgreementData key, Object value) {
		scenarioContext.put(key.toString(), value);
	}

	public static Object getAgreementContext(AgreementData key) {
		return scenarioContext.get(key.toString());
	}

	public static void setCommonContext(Common key, Object value) {
		scenarioContext.put(key.toString(), value);
	}

	public static Object getCommonContext(Common key) {
		return scenarioContext.get(key.toString());
	}

}
