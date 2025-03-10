package Generic_Utility;

import java.io.File;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

/**
 * *description: This method used to maximize the browser window
 * @return nothing
 * @author Prashanth K
 */
public class WebDriver_Utility {
	public void maximizeBrowserWindow(WebDriver driver) {
		driver.manage().window().maximize();
	}

	public void implicitWaitBrowser(WebDriver driver, int secondsToWait) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(secondsToWait));
	}
	
	/**
	 *  used to Switch to Any Window based on Window Title
	 * @param driver
	 * @param partialWindowTitle
	 */
	public void swithToWindow(WebDriver driver , String partialWindowTitle) {
	       Set<String> set = driver.getWindowHandles();
	         Iterator<String> it = set.iterator();

	          while (it.hasNext()) {
			          String wID = it.next();
			          driver.switchTo().window(wID);
			          String currentWindowTitle = driver.getTitle();
			          if(currentWindowTitle.contains(partialWindowTitle)) {
			        	  break;
			          }
		    	}
	}
	
	public void swithToPopupAndAcceptAlert(WebDriver driver) throws Throwable {
		driver.switchTo().alert();
		String alertMsg = driver.switchTo().alert().getText();
		System.out.println(alertMsg + " alert message is displayed");
		Thread.sleep(1000);
		driver.switchTo().alert().accept();
	}
	
	public void swithToPopupAndDismissAlert(WebDriver driver) throws Throwable {
		driver.switchTo().alert();
		String alertMsg = driver.switchTo().alert().getText();
		System.out.println(alertMsg + " alert message is displayed");
		Thread.sleep(1000);
		driver.switchTo().alert().dismiss();
	}
	
public static String takeScreenShotEx(WebDriver driver, String screenShotName) throws Throwable {
		
		TakesScreenshot takesSceenShot = (TakesScreenshot) driver;
		File src = takesSceenShot.getScreenshotAs(OutputType.FILE);
		File dst = new File("./ScreenShots/" + screenShotName +".png");
		FileUtils.copyFile(src, dst);
		return dst.getAbsolutePath();
	}
}
