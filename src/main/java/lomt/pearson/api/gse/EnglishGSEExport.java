package lomt.pearson.api.gse;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import lomt.pearson.common.BaseClass;
import lomt.pearson.common.LoadPropertiesFile;
import lomt.pearson.common.ReadGSEXLSFile;
import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.EnglishPOM;
import lomt.pearson.page_object.Login;

public class EnglishGSEExport extends BaseClass {
	
	private WebDriver driver;
	
	private Login login = null;
	private CommonPOM commonPOM = null;
	private EnglishPOM englishPOM = null;
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);

	public void getDriverInstance(WebDriver driver) {
		this.driver = initiliseDriver();
	}

	public void openBrowser() {
		getDriverInstance(driver);
		driver.manage().window().maximize();
		driver.get(environment);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

		commonPOM = new CommonPOM(driver);
		englishPOM = new EnglishPOM(driver);
	}
	
	public void login() {
		try {
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(pwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);
		} catch (Exception e) {
			e.printStackTrace();
			// add logger
		}
	}
	
	public void gseEducationalGoalFrameworkExport() throws InterruptedException {
		Assert.assertTrue(commonPOM.getEnglishLOB().isDisplayed());
		commonPOM.getEnglishLOB().click();
		
		Thread.sleep(1000);
		Assert.assertTrue(englishPOM.getGseLink().isEnabled());
		englishPOM.getGseLink().click();
	
		WebDriverWait wait1 = new WebDriverWait(driver,600); 
		wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.GSE_ACTION_LINK)));
		//wait1.until(ExpectedConditions.visibilityOfElementLocated(englishPOM.getGseActionLink()));
		
		Thread.sleep(2000);
		Assert.assertTrue(englishPOM.getGseStructure().isEnabled());
		englishPOM.getGseStructure().click();
		
		//wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.EG_FIRST_DIV)));
		// result count showed while loader is loading
		JavascriptExecutor jse = (JavascriptExecutor)driver;
		jse.executeScript("window.scrollBy(0,500)");
		
		Thread.sleep(16000);
		
		//commonPOM.getDescriptiveIdADSearch().sendKeys(LOMTConstant.DESCRIPTIVE_ID.trim()); 
		// Need to change according to gse sheet data
		
		Thread.sleep(3000);
		Assert.assertTrue(commonPOM.getUpdateResultButton().isEnabled());
		commonPOM.getUpdateResultButton().click();
		System.out.println("Clicked Update result button ******");
		//wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.EG_FIRST_DIV)));
		Thread.sleep(22000);
		
		commonPOM.getEgCheckbox().click();			
		Thread.sleep(3000);
		
		commonPOM.getExportButton().click();
		Thread.sleep(22000);
		
		String date = new Date().toString();
		String[] CurrentDate= date.substring(4).split(" ");	 
		String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];
		
		File exportedFile = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING + LOMTConstant.EXPORTED_FILE_NAME + formatedDate + LOMTConstant.XLSX_EXTENSION);
		File actualFile = new File(LOMTConstant.ACTUAL_FILE_PATH + LOMTConstant.EMPTY_STRING + LOMTConstant.ACTUAL_FILE_NAME + LOMTConstant.XLSX_EXTENSION);
		
		System.out.println("exportedFile : "+exportedFile);
		System.out.println("actualFile : "+actualFile);
		
		Thread.sleep(2000);
		if (exportedFile.isFile()) {
			ReadGSEXLSFile exportGSEObject = new ReadGSEXLSFile();
			try {
				System.out.println("GSE actual file path : " + actualFile);
				System.out.println("GSE exported file path : " + exportedFile);

				exportGSEObject.readGSEExportedFileEducationGoalFramework(actualFile, exportedFile);

				System.out.println("GSE exportis done : " + exportedFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
			} else {
			System.out.println("GSE file is not exported");
		}
		commonPOM.getPearsonLogo().click();
		Thread.sleep(2000);
	}
	
	/**
	 * Wire off export at Goalframework
	 * @throws InterruptedException
	 */
	/*public void gseGoalFrameworkExport() throws InterruptedException {
		Thread.sleep(3000);
		WebElement gseLOB = driver.findElement(By.xpath(LOMTConstant.ENGLISH_LOB_XPATH));
		gseLOB.click();
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		Assert.assertTrue(driver.findElement(By.xpath(LOMTConstant.GSE_LINK)).isEnabled());
		
		Thread.sleep(2000);
		WebElement selectGSEStructure = driver.findElement(By.xpath(LOMTConstant.GSE_LINK));
		selectGSEStructure.click();
		driver.manage().timeouts().implicitlyWait(2, TimeUnit.MINUTES);		
		Assert.assertTrue(driver.findElement(By.xpath(LOMTConstant.GSE_ACTION_LINK)).isDisplayed());
		
		Thread.sleep(2000);
		WebElement actionLink = driver.findElement(By.xpath(LOMTConstant.GSE_ACTION_LINK));
		actionLink.click();
		
		Thread.sleep(2000);
		Assert.assertTrue(driver.findElement(By.xpath(LOMTConstant.EXPORT_BUTTON)).isEnabled());
		
		WebElement exportButton = driver.findElement(By.xpath(LOMTConstant.EXPORT_BUTTON));
		exportButton.click();	
		//driver.manage().timeouts().implicitlyWait(5, TimeUnit.MINUTES);
		while (driver.findElement(By.xpath("//div[@class='loader']")).isDisplayed()) {
				Thread.sleep(500);
		}
		Thread.sleep(5000);
		WebElement pearsonHeaderLink = driver.findElement(By.xpath(LOMTConstant.PEARSON_HEADER_XPATH));
		pearsonHeaderLink.click();
		Thread.sleep(2000);
		
		String date = new Date().toString();
		String[] CurrentDate= date.substring(4).split(" ");	 
		String formatedDate = CurrentDate[1]+CurrentDate[0]+CurrentDate[4];
		
		File exportedFile = new File(LOMTConstant.EXPORTED_FILE_PATH + LOMTConstant.EMPTY_STRING + LOMTConstant.EXPORTED_FILE_NAME + formatedDate + LOMTConstant.XLS_EXTENSION);
		File actualFile = new File(LOMTConstant.ACTUAL_FILE_PATH + LOMTConstant.EMPTY_STRING + LOMTConstant.ACTUAL_FILE_NAME + LOMTConstant.XLS_EXTENSION);
		if (exportedFile.isFile()) {
			ReadGSEXLSFile exportGSEObject = new ReadGSEXLSFile();
			try {
				System.out.println("GSE actual file path : "+actualFile);
				System.out.println("GSE exported file path : "+exportedFile);
				
				exportGSEObject.readGSEExportedFile(actualFile, exportedFile);
				
				System.out.println("GSE exportis done : "+exportedFile);
			} catch (IOException e) {
				e.printStackTrace();
				//TODO: logger
			}
		} else {
			System.out.println("GSE file is not exported");
		}
	}*/
	
	public void closeDriverInstance() {
		driver.close();
	}

}
