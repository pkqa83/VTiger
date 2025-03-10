package com.qspider.vtiger.com.test.cases;


import org.openqa.selenium.WebDriver;

import org.testng.annotations.Test;

import Generic_Utility.BaseClass;
import Generic_Utility.Excel_Utility;

import Generic_Utility.Java_Utility;

import POM_Repo.HomePage;

import POM_Repo.OrganizationsPage;

public class TestNG001_CreateOrganization extends BaseClass{
	@Test(groups="SmokeTesting")
	public void TestNG001_CreateOrganization() throws Throwable {
		//===========================================================================================//
		Java_Utility jlib = new Java_Utility();
		Excel_Utility excelLib = new Excel_Utility();

		//===========================================================================================//
	
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
	}
	@Test(groups="SmokeTesting")
	public void testRegional() {
		System.out.println("Regional Testing method");
	}

}
