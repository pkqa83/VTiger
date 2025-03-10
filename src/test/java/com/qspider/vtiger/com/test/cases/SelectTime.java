package com.qspider.vtiger.com.test.cases;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Month;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
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

public class SelectTime {

	public static void main(String[] args) throws Throwable {
		//Launch chrome in Incognito mode
		ChromeOptions co = new ChromeOptions();
		co.addArguments("--incognito");
		WebDriver driver = new ChromeDriver(co);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.get("https://demoapps.qspiders.com/ui/timePick/timeClock?sublist=1");

		//Thread.sleep(2000);
		//Clicking the time element to display the clock
		WebElement cal = driver.findElement(By.xpath(
				"//input[@class='MuiInputBase-input MuiOutlinedInput-input Mui-readOnly MuiInputBase-readOnly css-1x5jdmq']"));
		cal.click();
		Thread.sleep(2000);

		//Thread.sleep(2000);
		//Getting the default time
		String defaultTime = driver
				.findElement(By.xpath("//div[@class='MuiTimePickerToolbar-hourMinuteLabel css-1l4fxj8']/button/span"))
				.getText();
		System.out.println("Default hour is " +defaultTime);
		
		WebElement hourTime = driver.findElement(By.xpath(
				"//div/div[@class='MuiClock-wrapper css-1nob2mz']/span[@class='MuiClockNumber-root css-1flhz3h'][text()='4']"));
		
		//Clicking on hour element using the Actions class 
		Actions action = new Actions(driver);
		action.click(hourTime).build().perform();
		Thread.sleep(2000);
		
		String selTime = driver
				.findElement(By.xpath("//div[@class='MuiTimePickerToolbar-hourMinuteLabel css-1l4fxj8']")).getText();
		System.out.println(selTime);

		//getting the AM field color to ensure that it gets highlighted 
		String color = driver.findElement(By.xpath("//div[@class='MuiTimePickerToolbar-ampmSelection css-1bt0dft']/button/span")).getCssValue("color");
		String backcolor = driver.findElement(By.xpath("//div[@class='MuiTimePickerToolbar-ampmSelection css-1bt0dft']/button/span")).getCssValue("background-color");
		
		System.out.println(color);
		System.out.println(backcolor);

		String minTime = driver
				.findElement(By.xpath("//div[@class='MuiTimePickerToolbar-ampmSelection css-1bt0dft']/button/span"))
				.getText();
		Thread.sleep(2000);
		
		//time validation
		if (selTime.contains("4") && minTime.contains("AM") && (!color.equals(backcolor))) {
			System.out.println("Selected time: " + selTime + minTime + " matches with expected time");
		} else {
			System.out.println("Selected time: " + selTime + minTime + " does not matches with expected time");
		}
		//driver.quit();
	}
}


/*
 * if (!color.equals(backcolor)) { System.out.println("Text is highlighted!"); }
 * else { System.out.println("Text is not highlighted!"); }
 */


//WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(20));
		//waits.until(ExpectedConditions.elementToBeClickable(By.xpath(
		//		"//div/div[@class='MuiClock-wrapper css-1nob2mz']/span[@class='MuiClockNumber-root css-1flhz3h'][text()='4']")));
		