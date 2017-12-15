package lomt.pearson.common;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import lomt.pearson.constant.LOMTConstant;
import lomt.pearson.page_object.CommonPOM;
import lomt.pearson.page_object.EnglishPOM;
import lomt.pearson.page_object.ExternalFrameworkPOM;
import lomt.pearson.page_object.HEPom;
import lomt.pearson.page_object.IntermediaryPOM;
import lomt.pearson.page_object.Login;
import lomt.pearson.page_object.NALSPom;
import lomt.pearson.page_object.ProductTocPOM;
import lomt.pearson.page_object.SGPom;
import lomt.pearson.page_object.SchoolPOM;

public class NonAdminUserBrowseExport extends BaseClass {
	
	private String environment = LoadPropertiesFile.getPropertiesValues(LOMTConstant.LOMT_ENVIRONMENT);
	
	private String userName = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME);
	private String pwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD);
	
	private String learningUser = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_USER);
	private String learningUserPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_USER_PWD);
	
	private String learningSME = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_SME);
	private String learningSMEPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_SME_PWD);
	
	private String learningEditor = LoadPropertiesFile.getPropertiesValues(LOMTConstant.USER_NAME_LEARNING_EDITOR);
	private String learningEditorPwd = LoadPropertiesFile.getPropertiesValues(LOMTConstant.PASSWORD_LEARNING_EDITOR);
	
	private WebDriver driver;

	private Login login = null;
	private CommonPOM commonPOM = null;
	private HEPom hePom = null;
	private NALSPom nalsPom  = null;
	private SGPom sgPom = null;
	private SchoolPOM schoolPOM = null;
	private ExternalFrameworkPOM exfPOM = null;
	private IntermediaryPOM intermediaryPOM = null;
	private EnglishPOM englishPOM = null;
	private ProductTocPOM productTocPOM = null;
	
	public void getDriverInstance(WebDriver driver) {
		this.driver = initialiseChromeDriver();
	}
	
	public void openBrowser() {
		getDriverInstance(driver);
		driver.manage().window().maximize();
		driver.get(environment);
		driver.manage().timeouts().implicitlyWait(1, TimeUnit.MINUTES);

		commonPOM = new CommonPOM(driver);
		hePom = new HEPom(driver);
		nalsPom = new NALSPom(driver);
		sgPom = new SGPom(driver);
		schoolPOM = new SchoolPOM(driver);
		exfPOM = new ExternalFrameworkPOM(driver);
		intermediaryPOM = new IntermediaryPOM(driver);
		englishPOM = new EnglishPOM(driver);
		productTocPOM = new ProductTocPOM(driver);
	}
	
	public void loginAdmin() {
		try {
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(pwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String loginLearningUser() {
		String userName = null;
		try {
			userName = learningUser;
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(learningUserPwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
	
	public String loginLearingSME() {
		String userName = null;
		try {
			userName = learningSME;
			login = new Login(driver);
			login.getUserName().sendKeys(userName);
			login.getPassword().sendKeys(learningSMEPwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
	
	public String loginLearningEditor() {
		try {
			userName = learningEditor;
			login = new Login(driver);
			login.getUserName().sendKeys(learningEditor);
			login.getPassword().sendKeys(learningEditorPwd);
			login.getLoginButton().click();
			driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userName;
	}
	
	public void logout() {
		try {
			commonPOM.getLogoutOption().click();
			Thread.sleep(2000);
			commonPOM.getLogout().click();
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public boolean englishGSEBrowseAndExport(ExtentTest logger, String userName) {
		boolean flag = false;
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 180); 
		try {
			Thread.sleep(4000);
			commonPOM.getEnglishLOB().click();			
			englishPOM.getGseLink().click();
		
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.GSE_ACTION_LINK)));
			
			Thread.sleep(200);
			englishPOM.getGseStructure().click();			
			//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(60000);
			
			englishPOM.getCancelFilterBySet().click();
			jse.executeScript("window.scrollBy(0,400)");	
			commonPOM.getUpdateResultButton().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,200)");	
			
			Thread.sleep(1000);
			
			//jse.executeScript("window.scrollBy(0,500)");
			
			if (commonPOM.getShowingGFText().getText().contains("Showing")) {
				removeExistingFile();
				englishPOM.getSelectAll().click();
				englishPOM.getRenderedLink().click();
				Thread.sleep(1000);
				
				commonPOM.getExportButton().click();
				//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(60000);
				
				String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
				if (exportedFileName.contains("Gse_Template_Level")) {
					flag = true;
				}
				jse.executeScript("window.scrollBy(0,-500)");
				englishPOM.getEnglishLink().click();
				Thread.sleep(1000);
			} else {
				jse.executeScript("window.scrollBy(0,-500)");
				englishPOM.getClearFunLink().click();
				
				jse.executeScript("window.scrollBy(0,400)");
				commonPOM.getUpdateResultButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,100)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					englishPOM.getSelectAll().click();
					englishPOM.getRenderedLink().click();
					Thread.sleep(1000);
					
					commonPOM.getExportButton().click();
					//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					Thread.sleep(60000);
					
					String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains("Gse_Template_Level")) {
						flag = true;
					}
					jse.executeScript("window.scrollBy(0,-500)");
					englishPOM.getEnglishLink().click();
					Thread.sleep(1000);
				} else {
					logger.log(LogStatus.FAIL, "English GSE either export or browse is failed, user "+userName);
					jse.executeScript("window.scrollBy(0,-500)");
					englishPOM.getEnglishLink().click();
					Thread.sleep(1000);
				}
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "English GSE export and browse successful "+userName);
			jse.executeScript("window.scrollBy(0,-500)");
			englishPOM.getEnglishLink().click();
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean englishEXFBrowseAndExport(ExtentTest logger, String userName) {
		boolean flag = false;
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		try {
			exfPOM.getExternalFrameworkStructureBrowseEnglish().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,500)");
			
			removeExistingFile();
			exfPOM.getActionLink().click();
			Thread.sleep(2000);
			commonPOM.getExfExport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(2000);
			String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			if (exportedFileName.contains("External_Framework_Template")) {
				flag = true;
			}
			commonPOM.getExfFirtBrowsedGF().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,-400)");
			flag = true;
			
			jse.executeScript("window.scrollBy(0,-400)");
			englishPOM.getEnglishLink().click();
			Thread.sleep(1000);
		} catch (Exception e) {
			jse.executeScript("window.scrollBy(0,-500)");
			englishPOM.getEnglishLink().click();
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean englishProductBrowseAndExport(ExtentTest logger, String userName) {
		boolean flag = false;
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		try {
			commonPOM.getEnglishLOB().click();
			commonPOM.getEnglishProductTOCStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			//jse.executeScript("window.scrollBy(0,500)");
			
			commonPOM.getTocEngEnterSearchTerm().sendKeys("Feldman_Product_TOC"); // css is generic
			Thread.sleep(1000);
			
			commonPOM.getTocEngUpdateBtn().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			
			jse.executeScript("window.scrollBy(0,400)");
			removeExistingFile();
			exfPOM.getActionLink().click();
			Thread.sleep(2000);
			commonPOM.getExfExport().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			//Thread.sleep(120000);
			
			String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
			if (exportedFileName.contains(".xlsx") || exportedFileName.contains(".xlsx") ) {
				flag = true;
			}
			commonPOM.getExfFirtBrowsedGF().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			flag = true;
			jse.executeScript("window.scrollBy(0,-800)");
			commonPOM.getPearsonLogo().click();
			Thread.sleep(1000);
			
		} catch (Exception e) {
			jse.executeScript("window.scrollBy(0,-800)");
			commonPOM.getPearsonLogo().click();
			flag = false;
			return flag;
		}
		return flag;
	}
	
	
	
	public void removeExistingFile() throws IOException {
		if (new File(LOMTConstant.EXPORTED_FILE_PATH).exists()) 
			FileUtils.cleanDirectory(new File(LOMTConstant.EXPORTED_FILE_PATH));
	}
	
	public String getFileFromDirectory(String filePath) {
		String exportedFileName = null;
		File folder = new File(filePath);
		File[] listOfFiles = folder.listFiles();
		for (int i = 0; i < listOfFiles.length; i++) {
		      if (listOfFiles[i].isFile()) {
		    	  exportedFileName = listOfFiles[i].getName();
		        System.out.println("File " + listOfFiles[i].getName());
		      } 
		    }
		return exportedFileName;
	}
	
	public void closeDriverInstance() {
		driver.manage().timeouts().implicitlyWait(05, TimeUnit.SECONDS);
		driver.close();
	}

}
