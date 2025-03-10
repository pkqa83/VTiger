package Generic_Utility;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import com.beust.jcommander.DynamicParameter;

import POM_Repo.HomePage;
import POM_Repo.LoginPage;


//import POM_Repo.HomePage;
//import POM_Repo.LoginPage;

public class BaseClass {

	public WebDriver driver;
	public static WebDriver sdriver;
	public File_Utility flib = new File_Utility();
	public Java_Utility jlib = new Java_Utility();
	public Excel_Utility excelLib = new Excel_Utility();
	public WebDriver_Utility webDrvLib= new WebDriver_Utility();
	
	@BeforeSuite(groups={"SmokeTesting", "SanityTesting", "RegressionTesting"})
	public void BS() {
		System.out.println("Establishing DataBase connection");
	}

	@BeforeTest(groups={"SmokeTesting", "SanityTesting", "RegressionTesting"})
	public void BT() {
		System.out.println("Parallel execution");
	}

	//@Parameters("BROWSER")
	@BeforeClass(groups={"SmokeTesting", "SanityTesting", "RegressionTesting"})
	//public void BC(String BROWSER) throws Throwable {
	public void BC() throws Throwable {
		
		File_Utility flib = new File_Utility();
        String BROWSER = flib.getDataFromPropertiesFile("browser");
        
        if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else {
			driver = new ChromeDriver();
		}

		System.out.println("Launching Browser");
		sdriver=driver;
	}

	@BeforeMethod(groups={"SmokeTesting", "SanityTesting", "RegressionTesting"})
	public void BM() throws Throwable {
        webDrvLib.maximizeBrowserWindow(driver);
		webDrvLib.implicitWaitBrowser(driver, 10);
		
		String USERNAME = flib.getDataFromPropertiesFile("username");
		String PASSWORD = flib.getDataFromPropertiesFile("password");
		String URL = flib.getDataFromPropertiesFile("url");
		
		driver.get(URL);
		LoginPage login = new LoginPage(driver);
		login.loginToVTiger(USERNAME, PASSWORD);
		System.out.println("Login to App");
	}

	@AfterMethod(groups={"SmokeTesting", "SanityTesting", "RegressionTesting"})
	public void AM() {
		HomePage homePage = new HomePage(driver);
		homePage.signOutFromVTiger();
		System.out.println("Logout from App");
	}

	@AfterClass(groups={"SmokeTesting", "SanityTesting", "RegressionTesting"})
	public void AC() {
		driver.quit();
		System.out.println("Close the browser");
	}

	@AfterTest(groups={"SmokeTesting", "SanityTesting", "RegressionTesting"})
	public void AT() {
		System.out.println("Terminsating Parallel execution");
	}

	@AfterSuite(groups={"SmokeTesting", "SanityTesting", "RegressionTesting"})
	public void AS() {
		System.out.println("Closing the DataBase Connection");
	}

}
 