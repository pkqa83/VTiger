package com.qspider.vtiger.com.test.cases;

import java.io.FileInputStream;
import java.time.Duration;
import java.util.List;
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
import POM_Repo.ProductsPage;


public class VTiger_TC005_Create_DismissDeleteProduct {

	public static void main(String[] args) throws Throwable {		
		File_Utility flib = new File_Utility();
		Java_Utility jlib = new Java_Utility();
		Excel_Utility excelLib = new Excel_Utility();
		WebDriver_Utility webDrvLib= new WebDriver_Utility();
		String BROWSER = flib.getDataFromPropertiesFile("browser");
		
		WebDriver driver = null;

		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}
		
		String URL = flib.getDataFromPropertiesFile("url");
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
	
		webDrvLib.maximizeBrowserWindow(driver);
		webDrvLib.implicitWaitBrowser(driver, 10);
		driver.get(URL);
		//div[@class='custom-day range locked' and contains(text(), '19')]
		/*
		 * driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		 * driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		 * driver.findElement(By.id("submitButton")).click();
		 * driver.findElement(By.linkText("Products")).click();
		 * driver.findElement(By.cssSelector("[alt=\"Create Product...\"]")).click();
		 */

		/*
		 * LoginPage login = new LoginPage(driver);
		 * login.getTxtUserName().sendKeys(USERNAME);
		 * login.getTxtPassword().sendKeys(PASSWORD); login.getBtnLogin().click();
		 */
		LoginPage login = new LoginPage(driver);
		login.loginToVTiger(USERNAME, PASSWORD);
		
		HomePage homePage = new HomePage(driver);
		homePage.clickProductsLink();
		
		int ranNum = jlib.getRandomValue();
		String cellValue=excelLib.getDataFromExcel("Product", 0, 0);		
		String prodName = cellValue + ranNum;
		System.out.println(prodName);

		ProductsPage productsPage = new ProductsPage(driver);
		productsPage.createProduct(prodName);
		
		driver.findElement(By.name("productname")).sendKeys(prodName);
		driver.findElement(By.cssSelector("[title=\"Save [Alt+S]\"]")).click();

		// ---------------------------------------------------------------------------------------------------
		String actData = driver.findElement(By.xpath("//span[@id='dtlview_Product Name']")).getText();
		if (actData.contains(prodName)) {
			System.out.println("Product: " + prodName + "  is created");
		} else {
			System.out.println("Product: " + prodName + " is not created");
		}

		driver.findElement(By.linkText("Products")).click();
		WebElement chkProdName = driver.findElement(By.xpath("//a[text()='"+prodName+"']/parent::td/preceding-sibling::td//input[@name='selected_id' and @type='checkbox']"));
		chkProdName.click();

		driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])")).click(); // change

		driver.switchTo().alert();
		String alertMsg = driver.switchTo().alert().getText();
		System.out.println(alertMsg + " alert message is displayed");
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
		Thread.sleep(1000);

		//driver.findElement(By.linkText("Products")).click();
		List<WebElement> listProdName = driver.findElements(By.xpath("(//table[@class='lvt small']/tbody/tr//td[3])[position()>1]"));
		boolean flagProdName=false;
		
		for(WebElement prodNameTable:listProdName) {
			String productName=prodNameTable.getText();
			if(productName.equals(prodName)) {
				flagProdName=true;
				break;
			}
		}
		
		if(flagProdName) {
			System.out.println("Product: " + prodName + " didn't delete after dismissing the alert pop-up and its working as expected");
		}
		else {
			System.out.println("Product: " + prodName + " is deleted even after dismissing the alert pop-up and its NOT working as expected");
		}

		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		// ===========================================================================
		Thread.sleep(2000);
		driver.quit();

	}

}