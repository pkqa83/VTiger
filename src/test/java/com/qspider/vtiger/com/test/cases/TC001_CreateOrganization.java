package com.qspider.vtiger.com.test.cases;

import java.io.FileInputStream;
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

import Generic_Utility.Excel_Utility;
import Generic_Utility.File_Utility;
import Generic_Utility.Java_Utility;
import Generic_Utility.WebDriver_Utility;
import POM_Repo.HomePage;
import POM_Repo.LoginPage;
import POM_Repo.OrganizationsPage;

public class TC001_CreateOrganization {

	public static void main(String[] args) throws Throwable {
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
		//===========================================================================================//
	
		int rndmNum = jlib.getRandomValue();
		String orgName = excelLib.getDataFromExcel("Organization", 0, 0) + rndmNum;
		String email = excelLib.getDataFromExcel("Organization", 1, 0);
		String phNum = excelLib.getDataFromExcelUsingDataFormatter("Organization", 2, 0);
		
		orgPage.createOrganization(orgName, email, phNum);
		orgPage.validateCreatedOrganization(orgName);		
		homePage.signOutFromVTiger();
		//driver.quit();
	}

}
