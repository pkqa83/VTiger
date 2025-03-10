package com.qspider.vtiger.com.test.cases;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic_Utility.Excel_Utility;
import Generic_Utility.File_Utility;
import Generic_Utility.Java_Utility;
import Generic_Utility.WebDriver_Utility;
import POM_Repo.HomePage;
import POM_Repo.LoginPage;
import POM_Repo.ProductsPage;

public class TC005_CreateAndDeleteProduct {

	public static void main(String[] args) throws Throwable {

		WebDriver driver;
		File_Utility fLib = new File_Utility();
		Java_Utility jLib = new Java_Utility();
		Excel_Utility excelLib = new Excel_Utility();
		WebDriver_Utility webDrvLib= new WebDriver_Utility();
		
		String BROWSER = fLib.getDataFromPropertiesFile("browser");
		String URL = fLib.getDataFromPropertiesFile("url");
		String USERNAME = fLib.getDataFromPropertiesFile("username");
		String PASSWORD = fLib.getDataFromPropertiesFile("password");
		
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		webDrvLib.maximizeBrowserWindow(driver);
		webDrvLib.implicitWaitBrowser(driver, 10);
		driver.get(URL);
		
		//===========================================================================================//
		LoginPage login = new LoginPage(driver);
		login.loginToVTiger(USERNAME, PASSWORD);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickProductsLink();
				
		//===========================================================================================//		
		int ranNum=jLib.getRandomValue();
		ProductsPage productPage = new ProductsPage(driver);
		String prodName =  excelLib.getDataFromExcel("Product", 0, 0)+ ranNum;
		System.out.println(prodName);
		productPage.createProduct(prodName);
		productPage.validateProduct(prodName);
		homePage.clickProductsLink();
		productPage.selectProductAndDelete(driver, prodName);
		
		productPage.validateProductAfterDelete(driver, prodName);
		driver.quit();

		
	}

}