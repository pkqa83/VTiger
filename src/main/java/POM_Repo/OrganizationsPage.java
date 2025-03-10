package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrganizationsPage {
	
	public OrganizationsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//img[@alt='Create Organization...']")
	private WebElement createOrganizationImg;
	
	@FindBy(name="accountname")
	private WebElement orgNameTxt;
	
	@FindBy(id="email1")
	private WebElement orgEmailTxt;
	
	@FindBy(name="phone")
	private WebElement orgphoneTxt;
		
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement btnSave; 
	
	@FindBy(xpath=	"//span[@id='dtlview_Organization Name']")
	private WebElement orgNameWebEle; 
	

	public WebElement getCreateOrganizationImg() {
		return createOrganizationImg;
	}
	
	public void validateOrganizationsPage() {
		if(createOrganizationImg.isDisplayed()) {
			System.out.println("Navigated to Organization successfully");
		}
		else {
			System.out.println("Couldn't navigated to Organization ");
		}
	}
	
	public void createOrganization(String orgName, String email, String phoneNum) {
		createOrganizationImg.click();		
		orgNameTxt.sendKeys(orgName);
		orgEmailTxt.sendKeys(email);
		orgphoneTxt.sendKeys(phoneNum);
		btnSave.click();
	}
	
	public void validateCreatedOrganization(String orgName) {
		if(orgNameWebEle.getText().contains(orgName)) {
			System.out.println("Organization " +orgName+ " is created successfully");
		} else {
			System.out.println("Organization "+orgName+"  is not created");
		}
	}
}
