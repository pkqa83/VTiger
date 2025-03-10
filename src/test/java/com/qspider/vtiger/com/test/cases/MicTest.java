package com.qspider.vtiger.com.test.cases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

import Generic_Utility.Excel_Utility;
import Generic_Utility.File_Utility;
import Generic_Utility.Java_Utility;
import Generic_Utility.WebDriver_Utility;
import POM_Repo.CampaignsPage;
import POM_Repo.HomePage;
import POM_Repo.LoginPage;
import POM_Repo.ProductsPage;

public class MicTest {

	public static void main(String[] args) throws Throwable {
		
		 ChromeOptions options = new ChromeOptions();
		    options.addArguments("use-fake-device-for-media-stream");
		    options.addArguments("use-fake-ui-for-media-stream");

		    DesiredCapabilities caps = new DesiredCapabilities();
		    caps.setCapability("browser", "Chrome");
		    caps.setCapability("browser_version", "133.0.6943.142");
		    caps.setCapability("os", "Windows");
		    caps.setCapability("os_version", "10");
		    caps.setCapability(ChromeOptions.CAPABILITY, options);
		    WebDriver driver = new ChromeDriver(options);
		    
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.get("https://mictests.com/ ");
			WebElement micButton= driver.findElement(By.id("mic-launcher"));
			micButton.click();

		
	}
}