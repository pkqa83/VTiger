package com.qspider.vtiger.com.test.cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import Generic_Utility.BaseClass;
import Generic_Utility.Excel_Utility;
import Generic_Utility.File_Utility;
import Generic_Utility.Java_Utility;
import Generic_Utility.WebDriver_Utility;
import POM_Repo.CampaignsPage;
import POM_Repo.HomePage;
import POM_Repo.LoginPage;
import POM_Repo.ProductsPage;

//@Listeners(Generic_Utility.ListenersImp.class)


//@Listeners(Generic_Utility.ExtentReport.class)
public class TestNG003_CreateCampaign extends BaseClass {
	@Test
	//@Test(retryAnalyzer = Generic_Utility.RetryAnalyserImp.class)
	public  void TestNG003_CreateCampaign() throws Throwable {
	
		File_Utility fLib = new File_Utility();
		Java_Utility jLib = new Java_Utility();
		Excel_Utility excelLib = new Excel_Utility();
		WebDriver_Utility webDrvLib= new WebDriver_Utility();
		
		HomePage homePage = new HomePage(driver);
		homePage.clickProductsLink();
				
		//===========================================================================================//		
		int ranNum=jLib.getRandomValue();
		homePage.navigateToCampaign();

		CampaignsPage campNPage = new CampaignsPage(driver);
		String campName =  excelLib.getDataFromExcel("Campaigns", 0, 0)+ ranNum;
		System.out.println(campName);
		Assert.fail("Failing the TC intentionally to validate the screenshot");
		campNPage.createCampaign(campName);
		Thread.sleep(1000);
		String actualValue=campNPage.validateCampaign(campName);
		Assert.assertEquals(actualValue, campName, "Campaign: " +campName+" is created successfully");
		System.out.println("Campaign: " +campName+" is NOT created");
	}

}