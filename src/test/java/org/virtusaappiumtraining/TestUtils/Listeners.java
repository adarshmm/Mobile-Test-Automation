package org.virtusaappiumtraining.TestUtils;

import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;

public class Listeners implements ITestListener {
	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest test = extent.createTest();
	}
	@Override
	public void onTestSuccess(ITestResult result) {
		
	}
	@Override
	public void onTestFailure(ITestResult result) {
		
	}
	@Override
	public void onTestSkipped(ITestResult result) {
		
	}
}
