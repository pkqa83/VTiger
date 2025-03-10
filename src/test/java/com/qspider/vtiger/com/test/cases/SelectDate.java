package com.qspider.vtiger.com.test.cases;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.Date;
import java.util.HashMap;

import Generic_Utility.Excel_Utility;
import Generic_Utility.File_Utility;
import Generic_Utility.Java_Utility;
import Generic_Utility.WebDriver_Utility;
import POM_Repo.CampaignsPage;
import POM_Repo.HomePage;
import POM_Repo.LoginPage;
import POM_Repo.ProductsPage;

public class SelectDate {

	public static void main(String[] args) throws Throwable {
		//Launch chrome in Incognito mode
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demoapps.qspiders.com/ui/datePick?sublist=0");

		//Clicking the calendar element to display the calendar
		WebElement cal = driver.findElement(By.xpath("//input[@placeholder='Select A Date' and @type='text']"));
		cal.click();
		Thread.sleep(2000);
	
		String calName = driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']")).getText();
		System.out.println("Current month and Year:" +calName);

		HashMap<String, String> hMap = new HashMap<String, String>();
		String regex = "[,\\.\\s]";
		String[] myArray = calName.split(regex);

		for (int i = 0; i < myArray.length; i++) {
			System.out.println(myArray[i]);
			if (i == 0) {
				hMap.put("monthName", myArray[i]);
			} else {
				hMap.put("yearNo", myArray[i]);
			}
			
			while (!(hMap.get("monthName").equalsIgnoreCase("April") && hMap.get("yearNo").equals("2025"))) {
				WebElement nextBtn = driver.findElement(
						By.xpath("//button[@class='react-datepicker__navigation react-datepicker__navigation--next']"));
				nextBtn.click();
				Thread.sleep(1000);

				calName = driver.findElement(By.xpath("//div[@class='react-datepicker__current-month']")).getText();
				hMap = new HashMap<String, String>();
				regex = "[,\\.\\s]";
				myArray = calName.split(regex);
				
				for (int j = 0; j < myArray.length; j++) {
					System.out.println(myArray[j]);
					if (j == 0) {
						hMap.put("monthName", myArray[j]);
					} else {
						hMap.put("yearNo", myArray[j]);
					}
				}
			}
			
		}
		WebElement reqDate=driver.findElement(By.xpath("//div[@class='react-datepicker__month-container']/div[@class='react-datepicker__month']/div/div[text()='5']"));
		reqDate.click();
		Thread.sleep(2000);
	}
	
}

/*
 * LocalDate currentDate = LocalDate.now();
 * //Get day from date int day = currentDate.getDayOfMonth();
 * 
 * // Get month from date Month month = currentDate.getMonth(); String month1 =
 * month.toString(); // Get year from date int year = currentDate.getYear();
 * 
 * // Print the day, month, and year System.out.println("Day: " + day);
 * System.out.println("Month: " + month); System.out.println("Year: " + year);
 * 
 * Thread.sleep(2000);
 */
			
/*
 * public String splitString(String myStr) { //myStr = "March 2025"; //String
 * str; String regex = "[,\\.\\s]"; String[] myArray = myStr.split(regex); for
 * (String str : myArray) { System.out.println(str); return str; } }
 */
/*
 * for (String str : myArray) { System.out.println(str); hMap.put("monthName",
 * str); }
 */

/*
 * WebElement date1=driver.findElement(By.xpath(
 * "//div[@class='react-datepicker__month-container']/div[@class='react-datepicker__month']/div/div"
 * )); String date=date1.getText(); while (!(date=="5")) { date1.click(); }
 */


/*
 * for (String str : myArray) { System.out.println(str); hMap.put("monthName",
 * str); }
 */


