package com.qspider.vtiger.com.test.cases;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import POM_Repo.FindAllBys;

public class TestFindAllBys {

	public static void main(String[] args) {
		WebDriver driver= new ChromeDriver();
		FindAllBys findall1 = new FindAllBys(driver);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://www.flipkart.com/");
		findall1.enterValue("iphone");
	}
}