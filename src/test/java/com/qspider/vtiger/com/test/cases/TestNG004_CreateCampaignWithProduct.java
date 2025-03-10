package com.qspider.vtiger.com.test.cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

public class TestNG004_CreateCampaignWithProduct  extends BaseClass{
	@Test(groups={"SmokeTesting", "RegressionTesting"})
	public void TestNG004_CreateCampaignWithProduct()  throws Throwable {

		File_Utility fLib = new File_Utility();
		Java_Utility jLib = new Java_Utility();
		Excel_Utility excelLib = new Excel_Utility();
		WebDriver_Utility webDrvLib= new WebDriver_Utility();
			
		HomePage homePage = new HomePage(driver);
		homePage.clickProductsLink();
				
		//===========================================================================================//		
		int ranNum=jLib.getRandomValue();
		ProductsPage productPage = new ProductsPage(driver);
		String prodName =  excelLib.getDataFromExcel("Product", 0, 0)+ ranNum;
		System.out.println(prodName);
		productPage.createProduct(prodName);
		productPage.validateProduct(prodName);
		homePage.navigateToCampaign();

		CampaignsPage campNPage = new CampaignsPage(driver);
		String campName =  excelLib.getDataFromExcel("Campaigns", 0, 0)+ ranNum;
		System.out.println(campName);
		campNPage.createCampaignWithProduct(driver,campName , prodName);
		Thread.sleep(1000);
		campNPage.validateCampaign(campName);
	}

}