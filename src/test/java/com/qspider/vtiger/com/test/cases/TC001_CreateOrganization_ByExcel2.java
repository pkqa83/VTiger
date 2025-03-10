package com.qspider.vtiger.com.test.cases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Generic_Utility.Excel_Utility;
import Generic_Utility.File_Utility;
import Generic_Utility.Java_Utility;
import Generic_Utility.WebDriver_Utility;
import POM_Repo.HomePage;
import POM_Repo.LoginPage;
import POM_Repo.OrganizationsPage;

public class TC001_CreateOrganization_ByExcel2 {

	@Test(dataProvider = "getOrganizationData")
	public void createOrganization(String orgName, String email, String phNum) throws Throwable {
		//===========================================================================================//
		WebDriver driver = null;
		//===========================================================================================//
		File_Utility flib = new File_Utility();
		Java_Utility jlib = new Java_Utility();
		Excel_Utility excelLib = new Excel_Utility();
		WebDriver_Utility webDrvLib= new WebDriver_Utility();
		
		//===========================================================================================//
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		String URL = flib.getDataFromPropertiesFile("url");
		
		//===========================================================================================//
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		//===========================================================================================//
		webDrvLib.maximizeBrowserWindow(driver);
		webDrvLib.implicitWaitBrowser(driver, 10);
		driver.get(URL);
			
		//===========================================================================================//
		LoginPage login = new LoginPage(driver);
		login.loginToVTiger(USERNAME, PASSWORD);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickOrganizationsLink();
		
		//===========================================================================================//
		OrganizationsPage orgPage=new OrganizationsPage(driver);
		orgPage.validateOrganizationsPage();
		int rndmNum = jlib.getRandomValue();
		String orgName1 = orgName + rndmNum;
		//===========================================================================================//
	
		orgPage.createOrganization(orgName1, email, phNum);
		orgPage.validateCreatedOrganization(orgName);		
		homePage.signOutFromVTiger();
		driver.quit();
	}

@DataProvider
public Object[][] getOrganizationData() throws Throwable{
	Excel_Utility excelLib = new Excel_Utility();
	Object[][] objArr=excelLib.readExcelDataForDataProvider("Organization");
	return objArr;
}
}