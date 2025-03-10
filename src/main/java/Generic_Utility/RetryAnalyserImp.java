package Generic_Utility;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestResult;

import Generic_Utility.BaseClass;

public class RetryAnalyserImp implements IRetryAnalyzer {

	public boolean retry(ITestResult result) {
		int count=0, retryCount=3;
		if(count<retryCount) {
			count++;
			return true;
		}
		return false;
	}
}