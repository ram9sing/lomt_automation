package lomt.pearson.common;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.page_object.Login;

public class LOMTCommon extends BaseClass {
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	
	private String userNameBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_USER);
	private String pwdBasic = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_USER_PWD);

	private WebDriver driver;
	
	private Login login = null;

	public LOMTCommon() {
		this.driver = initialiseChromeDriver();
		
		driver.manage().window().maximize();
		driver.get(environment);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		
		login = new Login(driver);
		login.getUserName().sendKeys(userName);
		login.getPassword().sendKeys(pwd);
		login.getLoginButton().click();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}
	
	/*public void openBrowser() {
		driver.manage().window().maximize();
		driver.get(environment);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
	}
	
	public void login() {
		try {
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(pwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
			// add logger
		}
	}*/
	
	public void closeDriverInstance() {
		driver.close();
	}

	public WebDriver getDriver() {
		return driver;
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

}
