package lomt.pearson.common;

import java.util.HashMap;

import lomt.pearson.constant.LOMTConstant;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

public class BaseClass {
	
	private WebDriver driver;

	public WebDriver initiliseDriver() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		driver = new ChromeDriver();
		
		return driver;
	}
	
	public WebDriver initialiseChromeDriver() {
		System.setProperty("webdriver.chrome.driver", "./drivers/chromedriver.exe");
		
		String downloadFilepath = LOMTConstant.EXPORTED_FILE_PATH;
		System.out.println("Downloaded file path : "+downloadFilepath);
		
		HashMap<String, Object> chromePrefs = new HashMap<String, Object>();
		chromePrefs.put("profile.default_content_settings.popups", 0);
		chromePrefs.put("download.default_directory", downloadFilepath);
		
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("prefs", chromePrefs);
		
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		cap.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
		cap.setCapability(ChromeOptions.CAPABILITY, options);
		driver = new ChromeDriver(cap);
		
		//driver = new ChromeDriver();
		
		return driver;
	}
	
	public WebDriver initialiseFirefoxDriver() {
		System.setProperty("webdriver.gecko.driver", "./drivers/geckodriver.exe");
		//driver = new FirefoxDriver();
		
		String downloadFilepath = LOMTConstant.EXPORTED_FILE_PATH;
		System.out.println("Downloaded file path : "+downloadFilepath);
		
		FirefoxProfile profile = new FirefoxProfile(); 
		profile.setPreference("browser.download.dir", downloadFilepath); 
		profile.setPreference("browser.download.folderList", 2);
		WebDriver driver = new FirefoxDriver(profile);
				
		
				
		return driver;
	}
	
	public WebDriver initialiseInternetExplorerDriver() {
		//TODO
				
		return null;
	}
}
