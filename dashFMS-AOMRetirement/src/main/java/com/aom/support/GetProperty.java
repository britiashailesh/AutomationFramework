package com.aom.support;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class GetProperty {
	private static Properties properties;
	private static Properties propertiesQuery;
	private static final String propertyFilePath = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\aom\\support\\config.properties";
	private static final String propertyFilePathQuery = System.getProperty("user.dir")
			+ "\\src\\main\\java\\com\\aom\\support\\query.properties";
	private static String URL = null;
	private static String environment = null;
	private static String serverName = null;
	private static String userName = null;
	private static String password = null;
	private static String query = null;

	public String getReportConfigPath() {
		String reportConfigPath = System.getProperty("user.dir")
				+ "\\src\\main\\java\\com\\aom\\support\\extent-config.xml";
		return reportConfigPath;
	}

	public static void setPropertiesFileReaderPath() {
		BufferedReader reader;

		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			properties = new Properties();
			try {
				properties.load(reader);
				reader.close();

			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		}
	}

	public static void setPropertiesQueryFileReaderPath() {
		BufferedReader queryReader;
		try {
			queryReader = new BufferedReader(new FileReader(propertyFilePathQuery));
			propertiesQuery = new Properties();
			try {
				propertiesQuery.load(queryReader);
				queryReader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException("query.properties not found at " + propertyFilePathQuery);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("query.properties not found at " + propertyFilePathQuery);
		}
	}

	public static String getDriverPath() {
		setPropertiesFileReaderPath();
		String driverPath = properties.getProperty("driverPath");
		if (driverPath != null)
			return driverPath;
		else
			throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
	}

	public static String getEnvironment() {
		setPropertiesFileReaderPath();
		String environment = properties.getProperty("ENVIRONMENT");

		if (environment != null)
			return environment;
		else
			throw new RuntimeException("Environment not specified in the Configuration.properties file.");
	}

	public static String getDBName() {
		setPropertiesFileReaderPath();
		String DBName = properties.getProperty("DATABASE_NAME");
		if (DBName != null)
			return DBName;
		else
			throw new RuntimeException("Database Name not specified in the Configuration.properties file.");
	}

	public static String getServerName() {
		setPropertiesFileReaderPath();
		environment = getEnvironment();
		switch (environment) {
		case "Capex":
			serverName = properties.getProperty("CAPEX_DB_SERVERNAME");
			break;
		case "KLO":
			serverName = properties.getProperty("KLO_DB_SERVERNAME");
			break;
		case "STG":
			serverName = properties.getProperty("STG_DB_SERVERNAME");
			break;
		case "DEV3":
			serverName = properties.getProperty("DEV3_DB_SERVERNAME");
			break;
		case "QA8":
			serverName = properties.getProperty("QA8_DB_SERVERNAME");
			break;
		}

		if (serverName != null)
			return serverName;
		else
			throw new RuntimeException("DB Server Name not specified in the Configuration.properties file.");
	}

	public static String getDBUserName() {
		setPropertiesFileReaderPath();
		environment = getEnvironment();
		switch (environment) {
		case "Capex":
			userName = properties.getProperty("CAPEX_DB_USERNAME");
			break;
		case "KLO":
			userName = properties.getProperty("KLO_DB_USERNAME");
			break;
		case "STG":
			userName = properties.getProperty("STG_DB_USERNAME");
			break;
		case "DEV3":
			userName = properties.getProperty("DEV3_DB_USERNAME");
			break;
		case "QA8":
			userName = properties.getProperty("QA8_DB_USERNAME");
			break;
		}
		if (userName != null)
			return userName;
		else
			throw new RuntimeException("DB Username not specified in the Configuration.properties file.");
	}

	public static String getDBPassword() {
		setPropertiesFileReaderPath();
		environment = getEnvironment();
		switch (environment) {
		case "Capex":
			password = properties.getProperty("CAPEX_DB_PASSWORD");
			break;
		case "KLO":
			password = properties.getProperty("KLO_DB_PASSWORD");
			break;
		case "STG":
			password = properties.getProperty("STG_DB_PASSWORD");
			break;
		case "DEV3":
			password = properties.getProperty("DEV3_DB_PASSWORD");
			break;
		case "QA8":
			password = properties.getProperty("QA8_DB_PASSWORD");
			break;
		}

		if (password != null)
			return password;
		else
			throw new RuntimeException("DB Password not specified in the Configuration.properties file.");
	}

	public static String getApplicationUrl(String environment) {
		setPropertiesFileReaderPath();
		switch (environment) {
		case "Capex":
			URL = properties.getProperty("CAPEX_URL");
			break;
		case "KLO":
			URL = properties.getProperty("KLO_URL");
			break;
		case "STG":
			URL = properties.getProperty("STG_URL");
			break;
		case "DEV3":
			URL = properties.getProperty("DEV3_URL");
			break;
		case "QA8":
			URL = properties.getProperty("QA8_URL");
			break;
		}
		if (URL != null)
			return URL;
		else
			throw new RuntimeException("URL not specified in the config.properties file.");

	}

	public static String getQuery(String queryName) {
		setPropertiesQueryFileReaderPath();
		if (propertiesQuery.containsKey(queryName)) {
			query = propertiesQuery.getProperty(queryName);
		}
		if (query != null)
			return query;
		else
			throw new RuntimeException("query not specified in the query.properties file.");

	}

}
