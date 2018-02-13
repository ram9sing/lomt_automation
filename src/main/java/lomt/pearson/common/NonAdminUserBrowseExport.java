package lomt.pearson.common;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
			driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
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
			commonPOM.getEnglishLOB().click();
			englishPOM.getGseLink().click();
			wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(LOMTConstant.GSE_ACTION_LINK)));

			englishPOM.getGseStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			Thread.sleep(10000);
			
			jse.executeScript("window.scrollBy(0,400)");

			if (commonPOM.getShowingGFText().getText().contains("Showing")) {
				removeExistingFile();
				englishPOM.getSelectAll().click();
				englishPOM.getRenderedLink().click();

				commonPOM.getExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));				
				Thread.sleep(35000);
				englishPOM.getFirstGSENode().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(20000);
				flag = true;
				
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
			} else {
				logger.log(LogStatus.FAIL, "No results found on English GSE browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Exception occured on the English GSE browse and export, logged user : " + userName);
			jse.executeScript("window.scrollBy(0,-800)");
			commonPOM.getPearsonLogo().click();
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean exfBrowseAndExport(ExtentTest logger, String userName, String lob) {
		boolean flag = false;
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		
		if (lob.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			try {
				commonPOM.getEnglishLOB().click();
				exfPOM.getExternalFrameworkStructureBrowseEnglish().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					commonPOM.getExfExport().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					/*String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains("External_Framework_Template")) {
						flag = true;
					}*/
					commonPOM.getExfFirtBrowsedGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,-800)");
					flag = true;
					
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on English External Framework browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-800)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the English External Framework during browse and export, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} else if (lob.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				commonPOM.getHeLOB().click();
				exfPOM.getExternalFrameworkStructureBrowseHE().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					commonPOM.getCommonExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					/*String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains("External_Framework_Template")) {
						flag = true;
					}*/
					commonPOM.getExfFirtBrowsedGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					jse.executeScript("window.scrollBy(0,-1000)");
					flag = true;
					
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on HE External Framework browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the HE External Framework during browse and export, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} 
		return flag;
	}
	
	public boolean productBrowseAndExport(ExtentTest logger, String userName, String lob) {
		boolean flag = false;
		
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		
		if (lob.equalsIgnoreCase(LOMTConstant.ENGLISH_LOB)) {
			try {
				commonPOM.getEnglishLOB().click();
				commonPOM.getEnglishProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				Thread.sleep(30000);
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					exfPOM.getActionLink().click();
					Thread.sleep(2000);
					commonPOM.getExfExport().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					/*String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains(".xlsx") || exportedFileName.contains(".xlsx") ) {
						flag = true;
					}*/
					commonPOM.getExfFirtBrowsedGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					flag = true;
					
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on English Product browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the English Product browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} else if(lob.equalsIgnoreCase(LOMTConstant.HE_LOB)) {
			try {
				commonPOM.getHeLOB().click();
				commonPOM.getHeProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(40000);
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();
					productTocPOM.getTocActionLink().click();
					Thread.sleep(2000);
					commonPOM.getCommonExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					/*String exportedFileName = getFileFromDirectory(LOMTConstant.EXPORTED_FILE_PATH);
					if (exportedFileName.contains(".xlsx") || exportedFileName.contains(".xlsx") ) {
						flag = true;
					}*/
					commonPOM.getTocFirstGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					flag = true;
					
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on HE Product browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the HE Product browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} else {
			//School
			try {
				commonPOM.getSchoolGlobalLOB().click();
				commonPOM.getSchoolProductTOCStructure().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));	
				Thread.sleep(20000);
				
				jse.executeScript("window.scrollBy(0,300)");
				
				if (commonPOM.getShowingGFText().getText().contains("Showing")) {
					removeExistingFile();

					productTocPOM.getActionLink().click();
					Thread.sleep(2000);
								
					commonPOM.getCommonExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					commonPOM.getExfFirtBrowsedGF().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					flag = true;
					
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
				} else {
					logger.log(LogStatus.FAIL, "No results found on School Product browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the School Product browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-800)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		}
		return flag;
	}
	
	public boolean heEducationalObjectiveAndExport(ExtentTest logger, String userName) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		try {
			commonPOM.getHeLOB().click();
			hePom.getEoStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,300)");
			
			if (commonPOM.getShowingGFText().getText().contains("Showing")) {
				removeExistingFile();
				schoolPOM.getAction().click();
				commonPOM.getCommonExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				hePom.getHegoalframework().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				flag = true;
				
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
			} else {
				logger.log(LogStatus.FAIL, "No results found on HE Educational Objetive browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Exception occured on the HE Educational Objetive browse page, logged user : " + userName);
			jse.executeScript("window.scrollBy(0,-1000)");
			commonPOM.getPearsonLogo().click();
			flag = false;
			return flag;
		}
		return flag;
	}
	
	public boolean getABAndCustomBrowseAndExport(ExtentTest logger, String userName, String lob) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		if (lob.equalsIgnoreCase(LOMTConstant.SCHOOL) || lob.equalsIgnoreCase(LOMTConstant.CUSTOM)) {
			try {
				commonPOM.getSchoolGlobalLOB().click();
				schoolPOM.getCurriculumSt().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));				
				jse.executeScript("window.scrollBy(0,250)");
				if(schoolPOM.getResultFound().getText().contains("Showing")) {
					removeExistingFile();
					//export
					schoolPOM.getAction().click();
					commonPOM.getCommonExportButton().click();
					wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
					
					jse.executeScript("window.scrollBy(0,100)");
					
					int counter = 0;
					List<WebElement> webElement  =  intermediaryPOM.getIntermediaryGFList(); // common for school CS and Intermediary
					if (!webElement.isEmpty()) {
						Iterator<WebElement> itr = webElement.iterator();
						while (itr.hasNext()) {
							WebElement childStructureElement = 	itr.next();
							String discipline = childStructureElement.getText();
							if (discipline.contains("ABXmlImport") || discipline.contains("ExternalFrameworkSpreadsheetImport")) {
								counter++;
								String xpath = "//div[@class='list-data-container']/child::div["+counter+"]/div/div[1]/div/span/span[2]/a";
								WebElement element = driver.findElement(By.xpath(xpath));
								element.click();
								wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
								break;
							}
						}
						flag = true;
						jse.executeScript("window.scrollBy(0,-1000)");
						commonPOM.getPearsonLogo().click();
					}
				} else {
					logger.log(LogStatus.FAIL, "No results found on School Global Curriculum Standard/Custom browse page, logged user : " + userName);
					jse.executeScript("window.scrollBy(0,-1000)");
					commonPOM.getPearsonLogo().click();
					flag = false;
					return flag;
				}
			} catch (Exception e) {
				logger.log(LogStatus.FAIL, "Exception occured on the School Global Curriculum Standard/Custom browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} 
		return flag;
	}
	
	public boolean getIntermediaryBrowseAndExport(ExtentTest logger, String userName) {
		boolean flag = false;
		JavascriptExecutor jse = (JavascriptExecutor) driver;			
		WebDriverWait wait = new WebDriverWait(driver, 300); 
		try {
			commonPOM.getSchoolGlobalLOB().click();
			intermediaryPOM.getIntermediaryStructure().click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
			jse.executeScript("window.scrollBy(0,300)");
			
			if (schoolPOM.getResultFound().getText().contains("Showing")) {
				removeExistingFile();
				schoolPOM.getIntAction().click();
				
				commonPOM.getCommonExportButton().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				
				commonPOM.getExfFirtBrowsedGF().click();
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(LOMTConstant.LOADER)));
				flag = true;
				
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
			} else {
				logger.log(LogStatus.FAIL, "No results found on School Global Intermediary browse page, logged user : " + userName);
				jse.executeScript("window.scrollBy(0,-1000)");
				commonPOM.getPearsonLogo().click();
				flag = false;
				return flag;
			}
		} catch (Exception e) {
			logger.log(LogStatus.FAIL, "Exception occured on the School Global Intermediary browse page, logged user : " + userName);
			jse.executeScript("window.scrollBy(0,-1000)");
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
