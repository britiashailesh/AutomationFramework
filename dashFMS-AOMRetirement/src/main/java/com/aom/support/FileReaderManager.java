package com.aom.support;

public class FileReaderManager {

	private static FileReaderManager fileReaderManager = new FileReaderManager();
	private static GetProperty GetProperty;

	private FileReaderManager() {
	}

	public static FileReaderManager getInstance() {
		return fileReaderManager;
	}

	public GetProperty getConfigReader() {
		return (GetProperty == null) ? new GetProperty() : GetProperty;
	}
}
