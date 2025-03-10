package Generic_Utility;

import java.io.FileInputStream;
import java.util.Properties;

public class File_Utility {
		public String getDataFromPropertiesFile(String key) throws Throwable {
			FileInputStream fis = new FileInputStream("E:\\SeleniumAutomation\\VTiger_Automation\\Test.properties");

			// step2:- load all the keys from properties file
			Properties pro = new Properties();
			pro.load(fis);

			// step3:- get keys from properties file
			String value = pro.getProperty(key);
			return value;
		}
	
}
