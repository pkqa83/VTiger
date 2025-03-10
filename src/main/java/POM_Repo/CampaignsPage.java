package POM_Repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import POM_Repo.ProductsPage;

import Generic_Utility.WebDriver_Utility;

public class CampaignsPage {
	
	public CampaignsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	WebDriver_Utility webDrvLib= new WebDriver_Utility();
	ProductsPage prodPage=new ProductsPage(null);
	
	@FindBy(xpath="//img[@alt='Create Campaign...']")
	private WebElement createCampaignImg;
	
	@FindBy(name="campaignname")
	private WebElement campaignNameTxt;
	
	@FindBy(css="[alt=\"Select\"]")
	private WebElement selectProductImg;
	
	@FindBy(xpath="//input[@id='search_txt']")
	private WebElement searchProdTxt;
		
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement btnSave; 
	
	@FindBy(name=	"search")
	private WebElement searchProdBtn; 
	
	@FindBy(xpath=	"//span[@id='dtlview_Campaign Name']")
	private WebElement campNfield; 
	
	public void createCampaign(String caampName) {
		createCampaignImg.click();		
		campaignNameTxt.sendKeys(caampName);
		btnSave.click();
	}
	
	public void createCampaignWithProduct(WebDriver driver, String caampName, String prodName) {
		createCampaignImg.click();		
		campaignNameTxt.sendKeys(caampName);
		selectProductImg.click();
		webDrvLib.swithToWindow(driver, "Products&action");
		searchProdTxt.sendKeys(prodName);
		searchProdBtn.click();
		//webDrvLib.selectPrdName(driver, prodName);
		prodPage.selectProductByName(driver, prodName);
		webDrvLib.swithToWindow(driver, "Campaigns&action");
		btnSave.click();
	}
	
	public String validateCampaign(String campaignName) {
		String actData = campNfield.getText();
		/*
		 * if (actData.contains(campaignName)) { System.out.println("Campaign: "
		 * +campaignName+" is created successfully"); } else {
		 * System.out.println("Campaign: " +campaignName+ " is not created"); }
		 */
		return actData;
	}
}
