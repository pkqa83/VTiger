package POM_Repo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public HomePage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(linkText="Organizations")
	private WebElement lnkOrganizations;
	
	@FindBy(xpath="//img[@src=\"themes/softed/images/user.PNG\"]")
	private WebElement userImg;
	
	@FindBy(xpath="//a[text()='Sign Out']")
	private WebElement signOutLnk;
	
	@FindBy(linkText="Products")
	private WebElement lnkProducts;
	
	@FindBy(linkText="More")
	private WebElement lnkMore;
	
	@FindBy(linkText="Campaigns")
	private WebElement lnkCampaigns;
	
	public WebElement getProductsLink() {
		return lnkProducts;
	}
	
	public void clickProductsLink() {
		lnkProducts.click();
	}
	
	public void navigateToCampaign() {
		lnkMore.click();
		lnkCampaigns.click();
	}
	
	public void clickOrganizationsLink() {
		lnkOrganizations.click();
	}
	
	public void signOutFromVTiger() {
		userImg.click();
		signOutLnk.click();
	}
	
	
}
