package POM_Repo;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import Generic_Utility.WebDriver_Utility;

public class ProductsPage {
	
	public ProductsPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	WebDriver_Utility webDrvLib= new WebDriver_Utility();
	
	@FindBy(xpath="//img[@alt='Create Product...']")
	private WebElement createProductLink;
	
	@FindBy(name="productname")
	private WebElement txtProductName;
	
	@FindBy(xpath="//input[@title='Save [Alt+S]']")
	private WebElement btnSave; 
	
	@FindBy(xpath=	"//span[@id='dtlview_Product Name']")
	private WebElement prodNameField; 
	
	@FindBy(xpath = "//input[@value='Delete']")
	private WebElement deleteProduct;
	
	public WebElement getCreateProductLink() {
		return createProductLink;
	}
	
	public WebElement getTxtProductName() {
		return txtProductName;
	}

	public void createProduct(String productName) {
		createProductLink.click();
		txtProductName.sendKeys(productName);
		btnSave.click();
	}
	
	public void validateProduct(String productName) {
		String actData = prodNameField.getText();
		if (actData.contains(productName)) {
			System.out.println("Product: " +productName+" is created successfully");
		} else {
			System.out.println("Product: " +productName+ " is not created");
		}
	}
	
	public void selectProductByName(WebDriver driver, String name) {
		driver.findElement(By.xpath("//a[text()='" + name + "']")).click();
	}
		
	public void selectProductAndDelete(WebDriver driver, String productName) throws Throwable {
		WebElement chkProdName = driver.findElement(By.xpath("//a[text()='"+productName+"']/parent::td/preceding-sibling::td//input[@name='selected_id' and @type='checkbox']"));
		chkProdName.click();
		deleteProduct.click();
		webDrvLib.swithToPopupAndAcceptAlert(driver);		
	}
	
	public void validateProductAfterDelete(WebDriver driver,String prodNama)
	{
		List<WebElement> allProd = driver.findElements(By.xpath("(//table[@class='lvt small']/tbody/tr//td[3])[position()>1]"));
		boolean prodFlag=false;
		
		for (WebElement prdName : allProd)
		{
			String actData = prdName.getText();
			if(actData.contains(prodNama))
			{
				prodFlag=true;
				break;
			}
		}
		if(prodFlag)
		{
			System.out.println("Product " +prodNama+ " is deleted successfully");
		}
		else
		{
			System.out.println("Couldn't delete a Product " +prodNama);
		}
		
	}
}
