package POM_Repo;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	
	
	
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(name="user_name")
	private WebElement txtUserName;
	
	@FindBy(name="user_password")
	private WebElement txtPassword;
	
	@FindBy(id="submitButton")
	private WebElement btnLogin;

	public WebElement getTxtUserName() {
		return txtUserName;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}
	
	public void loginToVTiger(String username, String password) {
		txtUserName.sendKeys(username);
		txtPassword.sendKeys(password);
		btnLogin.click();
	}
	
}
