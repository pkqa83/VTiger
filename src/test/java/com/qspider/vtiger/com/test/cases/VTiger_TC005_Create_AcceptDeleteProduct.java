package com.qspider.vtiger.com.test.cases;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import java.util.Properties;
import java.util.Random;
import java.util.Set;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import org.openqa.selenium.By;
//org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import Generic_Utility.Java_Utility;

public class VTiger_TC005_Create_AcceptDeleteProduct {

	public static void main(String[] args) throws Throwable {

		FileInputStream fis = new FileInputStream("E:\\SeleniumAutomation\\VTiger_Automation\\Test.properties");

		// step2:- load all the keys from properties file
		Properties pro = new Properties();
		pro.load(fis);

		// step3:- get keys from properties file
		String BROWSER = pro.getProperty("browser");
		String URL = pro.getProperty("url");
		String USERNAME = pro.getProperty("username");
		String PASSWORD = pro.getProperty("password");

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

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get(URL);
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		driver.findElement(By.linkText("Products")).click();
		driver.findElement(By.cssSelector("[alt=\"Create Product...\"]")).click();

		// Random Approach
		Java_Utility jlib = new Java_Utility();
		int ranNum = jlib.getRandomValue();
		
		// step1:- connecting the excel file
		FileInputStream file = new FileInputStream("E:\\SeleniumAutomation\\VTiger_Automation\\TestData.xlsx");

		// step2:- keep excel in read mode
		Workbook book = WorkbookFactory.create(file);

		// step3:- navigate into excel sheet
		Sheet sheet = book.getSheet("Product");

		// step4:- navigate into row
		Row row = sheet.getRow(0);

		// step5:-navigate into cell
		Cell cell = row.getCell(0);

		String prodName = cell.getStringCellValue() + ranNum;
		System.out.println(prodName);

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
//		Thread.sleep(5000);
		WebElement chkProdName = driver.findElement(By.xpath("//a[text()='"+prodName+"']/parent::td/preceding-sibling::td//input[@name='selected_id' and @type='checkbox']"));
		chkProdName.click();
		/*
		 * Actions action = new Actions(driver);
		 * action.click(chkProdName).build().perform();
		 */
		driver.findElement(By.xpath("(//input[@class='crmbutton small delete'])")).click(); // change

		driver.switchTo().alert();
		String alertMsg = driver.switchTo().alert().getText();
		System.out.println(alertMsg + " alert message is displayed");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
		Thread.sleep(1000);

		driver.findElement(By.linkText("Products")).click();
		// WebElement prodNameAfterCreate =
		// driver.findElement(By.xpath("//a[text()='"+prodName+"']"));
		// WebDriverWait wait = new WebDriverWait(driver, 10);
		/*
		 * WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		 * wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(
		 * "//a[text()='" + prodName + "']")));
		 */
		//Thread.sleep(2000);
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
		/*
		 * 
		 * 
		 * WebElement noProdFound =
		 * driver.findElement(By.xpath("//span[@class='genHeaderSmall']"));
		 * 
		 * if (noProdFound.isDisplayed()) { System.out.println("Product: " + prodName +
		 * " has been deleted successfully");
		 * 
		 * } else { System.out.println("Failed to delete a Product: " + prodName); }
		 */

		driver.findElement(By.xpath("//img[@src=\"themes/softed/images/user.PNG\"]")).click();
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		// ===========================================================================
		Thread.sleep(2000);
		driver.quit();

	}

}