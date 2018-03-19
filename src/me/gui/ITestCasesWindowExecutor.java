package me.gui;

import java.lang.reflect.Method;

public interface ITestCasesWindowExecutor {

	void setNumberOfMutants(int i);

	void setCurrentMutant(int i);

	void log(String string);

	void logError(String string);

	void setTestCases(Method[] methodsWithTestCases);

	int addVersionRow(String versionPath, int length);

	void log(String string, int row, int column);

	boolean hasTestCases();
}
