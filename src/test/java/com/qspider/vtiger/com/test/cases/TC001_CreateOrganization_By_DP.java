package com.qspider.vtiger.com.test.cases;

import java.time.Duration;
import java.util.Properties;
import java.util.Random;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

public class TC001_CreateOrganization_By_DP {

	//public static void main(String[] args) throws Throwable {
	@Test(dataProvider = "getOrganizationData")
	public void createOrganization(String orgName, String email, String phNum) throws Throwable {
		//===========================================================================================//
		WebDriver driver;
		//===========================================================================================//
		File_Utility flib = new File_Utility();
		Java_Utility jlib = new Java_Utility();
		Excel_Utility excelLib = new Excel_Utility();
		WebDriver_Utility webDrvLib= new WebDriver_Utility();
		
		//===========================================================================================//
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		String miTime = flib.getDataFromPropertiesFile("minT");
		String URL = flib.getDataFromPropertiesFile("url");
		System.out.println(miTime);
		System.out.println(System.getProperty("user.dir"));
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
		//===========================================================================================//
	

		/*
		 * String orgName = excelLib.getDataFromExcel("Organization", 0, 0) + rndmNum;
		 * String email = excelLib.getDataFromExcel("Organization", 1, 0); String phNum
		 * = excelLib.getDataFromExcelUsingDataFormatter("Organization", 2, 0);
		 */
		
		orgPage.createOrganization(orgName, email, phNum);
		orgPage.validateCreatedOrganization(orgName);		
		homePage.signOutFromVTiger();
		driver.quit();
	}

@DataProvider
public Object[][] getOrganizationData(){
	Java_Utility jlib = new Java_Utility();
	int rndmNum = jlib.getRandomValue();
	
	Object[][] objArr = new Object[3][3];
	objArr[0][0]="AAA" + rndmNum;
	objArr[0][1]="test123@gmail.com";
	objArr[0][2]="9844962433";
	
	objArr[1][0]="BBB" + rndmNum;
	objArr[1][1]="test1234@gmail.com";
	objArr[1][2]="9844962434";
	
	objArr[2][0]="CCC" + + rndmNum;
	objArr[2][1]="test1235@gmail.com";
	objArr[2][2]="9844962435";
	return objArr;
}
}

